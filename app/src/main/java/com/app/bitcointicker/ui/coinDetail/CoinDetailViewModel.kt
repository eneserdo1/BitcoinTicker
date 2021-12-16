package com.app.bitcointicker.ui.coinDetail

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.bitcointicker.data.entities.CoinDetail
import com.app.bitcointicker.data.entities.CoinList
import com.app.bitcointicker.data.entities.FavouriteCoin
import com.app.bitcointicker.data.entities.State.Status
import com.app.bitcointicker.data.repository.Repository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(val repository: Repository):ViewModel() {

    private val _coinDetailResponse = MutableLiveData<CoinDetail>()
    val coinDetailResponse = _coinDetailResponse
    var addFirestoreState: MutableLiveData<Boolean> = MutableLiveData()
    var deleteFirestoreState: MutableLiveData<Boolean> = MutableLiveData()
    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
    val loading = MutableLiveData<Boolean>()

    fun getCoinDetail(id:String){
        viewModelScope.launch {
            repository.getCurrencyDetail(id).collect {
                when(it.status){
                    Status.ERROR->{
                        loading.postValue(false)
                        _coinDetailResponse.postValue(null)
                    }
                    Status.LOADING->{
                        loading.postValue(true)
                    }
                    Status.SUCCESS->{
                        loading.postValue(false)
                        _coinDetailResponse.postValue(it.data)
                    }
                }
            }
        }
    }


    fun addFirestore(favouriteCoin: FavouriteCoin) {
        println("Fav coin $favouriteCoin")
        db.collection(auth.uid.toString()).document(favouriteCoin.coinDetail!!.id.toString()).set(favouriteCoin)
            .addOnSuccessListener {
                addFirestoreState.postValue(true)
            }.addOnFailureListener {
                addFirestoreState.postValue(false)
            }
    }


    fun deleteFirestore(favouriteCoin: FavouriteCoin) {
        db.collection(auth.uid.toString()).document(favouriteCoin.coinDetail!!.id.toString()).delete()
            .addOnSuccessListener {
                deleteFirestoreState.postValue(true)
            }.addOnFailureListener {
                deleteFirestoreState.postValue(true)
            }
    }
}