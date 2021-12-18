package com.app.bitcointicker.ui.coinDetail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.bitcointicker.data.entities.CoinDetail
import com.app.bitcointicker.data.entities.CoinList
import com.app.bitcointicker.data.entities.FavouriteCoin
import com.app.bitcointicker.data.entities.State.Status
import com.app.bitcointicker.data.local.LocalDataManager
import com.app.bitcointicker.data.repository.Repository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(val repository: Repository,val localDataManager: LocalDataManager):ViewModel() {

    private val _coinDetailResponse = MutableLiveData<CoinDetail>()
    val coinDetailResponse get() = _coinDetailResponse
    var addFirestoreState: MutableLiveData<Boolean> = MutableLiveData()
    var deleteFirestoreState: MutableLiveData<Boolean> = MutableLiveData()
    var isCoinFav: MutableLiveData<String> = MutableLiveData()
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


    fun addFirestore(favouriteCoin: FavouriteCoin,context: Context) {
        loading.postValue(true)
        db.collection(auth.uid.toString()).document(favouriteCoin.coinDetail!!.id.toString()).set(favouriteCoin)
            .addOnSuccessListener {
                addFirestoreState.postValue(true)
                setLocalData(favouriteCoin.coinDetail.id.toString(),context)
                loading.postValue(false)
            }.addOnFailureListener {
                addFirestoreState.postValue(false)
                loading.postValue(false)
            }
    }


    fun deleteFirestore(id: String,context: Context) {
        loading.postValue(true)
        db.collection(auth.uid.toString()).document(id).delete()
            .addOnSuccessListener {
                deleteFirestoreState.postValue(true)
                removeLocalData(id,context)
                loading.postValue(false)
            }.addOnFailureListener {
                deleteFirestoreState.postValue(true)
                loading.postValue(false)
            }
    }

    fun setLocalData(id: String, context: Context) {
        // Favoriye eklenen coinin id'sini sharedprefence'e ekliyoruz
        localDataManager.setSharedPreferences(context, id, id)
    }

    fun removeLocalData(id: String, context: Context) {
        // Favoriden kaldırılan coinin id'sini sharedprefence'dan siliyoruz
        localDataManager.removeSharedprefence(context, id)
    }

    fun getLocalData(id: String, context: Context): LiveData<String> {
        // Sharedpreference'den tıklanılan coinin favori durumunu çekiyoruz
        val favButtonState = localDataManager.getSharedPreference(context, id)
        isCoinFav.postValue(favButtonState)
        return isCoinFav
    }
}