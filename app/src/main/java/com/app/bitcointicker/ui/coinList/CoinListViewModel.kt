package com.app.bitcointicker.ui.coinList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.bitcointicker.data.entities.CoinList
import com.app.bitcointicker.data.entities.State.Status
import com.app.bitcointicker.data.repository.Repository
import com.app.bitcointicker.ui.coinList.adapter.CoinRecyclerviewAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(private val repository: Repository):ViewModel() {

    private val _coinListResponse = MutableLiveData<List<CoinList>>()
    val coinListResponse = _coinListResponse
    val loading = MutableLiveData<Boolean>()

    fun getCoinList(){
        val queryMap = HashMap<String, String>()
        queryMap["include_platform"] = "false"
        viewModelScope.launch {
            repository.getCurrency(queryMap).collect {
                when(it.status){
                    Status.ERROR->{
                        loading.postValue(false)
                        _coinListResponse.postValue(null)
                    }
                    Status.LOADING->{
                        loading.postValue(true)
                    }
                    Status.SUCCESS->{
                        loading.postValue(false)
                        _coinListResponse.postValue(it.data)
                    }
                }
            }
        }
    }

}