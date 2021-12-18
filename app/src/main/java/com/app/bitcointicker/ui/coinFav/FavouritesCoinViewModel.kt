package com.app.bitcointicker.ui.coinFav

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.bitcointicker.data.entities.CoinDetail
import com.app.bitcointicker.data.entities.FavouriteCoin
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouritesCoinViewModel @Inject constructor() : ViewModel() {

    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    val favouriteCoinList = MutableLiveData<ArrayList<FavouriteCoin>>()
    val loading = MutableLiveData<Boolean>()

    fun getFavorieList() {
        loading.postValue(true)
        db.collection(auth.uid.toString()).get()
            .addOnSuccessListener { response->
            val favories : ArrayList<FavouriteCoin> = arrayListOf()
            for (doc in response){
               val favCoin = favCoinConverter(doc)
                favories.add(favCoin)
            }
            loading.postValue(false)
            favouriteCoinList.postValue(favories)
        }.addOnFailureListener {
                favouriteCoinList.postValue(null)
                loading.postValue(false)
            }

    }

    private fun favCoinConverter(doc: QueryDocumentSnapshot?): FavouriteCoin {
        val data = Gson().toJson(doc!!.data)
        val coin = Gson().fromJson(data,FavouriteCoin::class.java)
        return coin
    }
}