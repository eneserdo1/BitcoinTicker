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
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesCoinViewModel @Inject constructor(
    val auth: FirebaseAuth,
    val db : FirebaseFirestore,
    val repository: Repository) : ViewModel() {

    val favories : ArrayList<FavouriteCoin> = arrayListOf()
    val favouriteCoinList = MutableLiveData<ArrayList<FavouriteCoin>>()
    val loading = MutableLiveData<Boolean>()

    fun getFavorieList() {
        loading.postValue(true)
        db.collection(auth.uid.toString()).get()
            .addOnSuccessListener { response->
            val favories : ArrayList<FavouriteCoin> = arrayListOf()
            for (doc in response){
                val favorieCoin = favorieCoinConverter(doc)
                favories.add(favorieCoin)
                refreshCoin(favorieCoin)
            }
            loading.postValue(false)
            favouriteCoinList.postValue(favories)
        }.addOnFailureListener {
                favouriteCoinList.postValue(null)
                loading.postValue(false)
            }
    }


    fun getFavorieListener(){
        db.collection(auth.uid.toString()).addSnapshotListener{docSnapshot,m->
            docSnapshot?.forEach { doc->
                val favorieCoin = favorieCoinConverter(doc)
                favories.filter { it.coinDetail!!.id == favorieCoin.coinDetail!!.id }.forEach {
                    favories.remove(it)
                }
                favories.add(favorieCoin)
                loading.postValue(false)
                favouriteCoinList.postValue(favories)
            }
        }
    }

    fun getFavCoinDetail(coin:FavouriteCoin){
        viewModelScope.launch {
            repository.getCurrencyDetail(coin.coinDetail!!.id.toString()).collect { response->
                when(response.status){
                    Status.SUCCESS->{
                        println("İd = ${response.data}")
                        updateFirestore(FavouriteCoin(coin.interval,response.data))
                    }
                    Status.LOADING->{

                    }
                    Status.ERROR->{

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
        val coin = Gson().fromJson(data,FavouriteCoin::class.java)
        return coin
    }

    private fun refreshCoin(coin: FavouriteCoin){
        updateCoin(coin)
        coin.interval?.let{
            Handler(Looper.getMainLooper()).postDelayed({
                viewModelScope.launch {
                    refreshCoin(coin)
                }
            },it)
        }
    }

    private fun updateCoin(coin: FavouriteCoin){
        getFavCoinDetail(coin)
    }
}