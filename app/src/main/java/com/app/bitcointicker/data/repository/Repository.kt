package com.app.bitcointicker.data.repository

import com.app.bitcointicker.data.entities.CoinDetail
import com.app.bitcointicker.data.entities.CoinList
import com.app.bitcointicker.data.entities.State.Resource
import com.app.bitcointicker.data.remoteDataSource.DataSourceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor(private val remoteDataSource: DataSourceImpl) {

    suspend fun getCurrency(queryMap: HashMap<String, String>): Flow<Resource<List<CoinList>>> {
        return flow {
            emit(remoteDataSource.getCurrency(queryMap))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCurrencyDetail(id:String):Flow<Resource<CoinDetail>>{
        return flow {
            emit(remoteDataSource.getCurrencyDetail(id))
        }.flowOn(Dispatchers.IO)
    }
}