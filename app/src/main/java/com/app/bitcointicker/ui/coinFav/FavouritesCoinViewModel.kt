package com.app.bitcointicker.ui.coinFav

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.bitcointicker.data.entities.FavouriteCoin
import com.app.bitcointicker.data.entities.State.Status
import com.app.bitcointicker.data.repository.Repository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesCoinViewModel @Inject constructor(
    val auth: FirebaseAuth,
    val db: FirebaseFirestore,
    val repository: Repository
) : ViewModel() {

    val favories: ArrayList<FavouriteCoin> = arrayListOf()
    val favouriteCoinList = MutableLiveData<ArrayList<FavouriteCoin>>()
    val loading = MutableLiveData<Boolean>()
    private var listenerRegistration: ListenerRegistration? = null

    fun getFavorieList() {
        loading.postValue(true)
        db.collection(auth.uid.toString()).get()
            .addOnSuccessListener { response ->
                for (doc in response) {
                    val favorieCoin = favorieCoinConverter(doc)
                    favories.add(favorieCoin)
                    refreshCoin(favorieCoin)
                }

                favouriteCoinList.postValue(favories)
                getFavorieListener()
                loading.postValue(false)
            }.addOnFailureListener {
                favouriteCoinList.postValue(null)
                loading.postValue(false)
            }
    }


    fun getFavorieListener() {
        val firebaseQuery = db.collection(auth.uid.toString())
        listenerRegistration = firebaseQuery.addSnapshotListener { docSnapshot, m ->
            docSnapshot?.forEach { doc ->
                val favorieCoin = favorieCoinConverter(doc)
                favories.filter { it.coinDetail!!.id == favorieCoin.coinDetail!!.id }.forEach {
                    favories.remove(it)
                }
                favories.add(favorieCoin)
                favouriteCoinList.postValue(favories)
            }
        }
    }

    fun getFavCoinDetail(coin: FavouriteCoin) {
        viewModelScope.launch {
            repository.getCurrencyDetail(coin.coinDetail!!.id.toString()).collect { response ->
                when (response.status) {
                    Status.SUCCESS -> {
                        updateFirestore(FavouriteCoin(coin.interval, response.data))
                    }
                    Status.LOADING -> {

                    }
                    Status.ERROR -> {

                    }
                }
            }
        }
    }

    private fun updateFirestore(favouriteCoin: FavouriteCoin) {
        db.collection(auth.uid.toString()).document(favouriteCoin.coinDetail!!.id.toString())
            .set(favouriteCoin)
    }


    private fun favorieCoinConverter(doc: QueryDocumentSnapshot?): FavouriteCoin {
        val data = Gson().toJson(doc!!.data)
        val coin = Gson().fromJson(data, FavouriteCoin::class.java)
        return coin
    }

    private fun refreshCoin(coin: FavouriteCoin) {
        updateCoin(coin)
        coin.interval?.let {
            Handler(Looper.getMainLooper()).postDelayed({
                viewModelScope.launch {
                    refreshCoin(coin)
                }
            }, it)
        }
    }

    private fun updateCoin(coin: FavouriteCoin) {
        getFavCoinDetail(coin)
    }

    override fun onCleared() {
        super.onCleared()
        listenerRegistration?.let {
            it.remove()
        }
    }


}