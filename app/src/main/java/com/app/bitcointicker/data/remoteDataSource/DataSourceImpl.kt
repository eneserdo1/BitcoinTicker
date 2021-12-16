package com.app.bitcointicker.data.remoteDataSource

import com.app.bitcointicker.data.entities.CoinDetail
import com.app.bitcointicker.data.entities.CoinList
import com.app.bitcointicker.data.entities.State.Resource
import com.app.bitcointicker.data.network.ApiService
import javax.inject.Inject


class DataSourceImpl @Inject constructor(private val apiService : ApiService): DataSource() {

    suspend fun getCurrency(queryMap:Map<String,String>): Resource<List<CoinList>> {
        return getResponse(request = {apiService.getCurrency(queryMap)},"Error fetch currency")
    }

    suspend fun getCurrencyDetail(id:String):Resource<CoinDetail>{
       return getResponse(request = {apiService.getCurrencyDetail(id)},"Error fetch currency detail")
    }

}