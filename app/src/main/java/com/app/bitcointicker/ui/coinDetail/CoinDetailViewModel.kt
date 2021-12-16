package com.app.bitcointicker.ui.coinDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.bitcointicker.data.entities.CoinDetail
import com.app.bitcointicker.data.entities.CoinList
import com.app.bitcointicker.data.entities.State.Status
import com.app.bitcointicker.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(val repository: Repository):ViewModel() {

    private val _coinDetailResponse = MutableLiveData<CoinDetail>()
    val coinDetailResponse = _coinDetailResponse
    val loading = MutableLiveData<Boolean>()

    fun getCoinDetail(id:String){
        viewModelScope.launch {
            repository.getCurrencyDetail(id).collect {
                when(it.status){
                    Status.ERROR->{
                        println("DetailVM - Error")
                        loading.postValue(false)
                        _coinDetailResponse.postValue(null)
                    }
                    Status.LOADING->{
                        println("DetailVM - Loading")
                        loading.postValue(true)
                    }
                    Status.SUCCESS->{
                        println("DetailVM - Success")
                        loading.postValue(false)
                        _coinDetailResponse.postValue(it.data)
                    }
                }
            }
        }
    }
}