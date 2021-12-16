package com.app.bitcointicker.data.network

import com.app.bitcointicker.data.entities.CoinDetail
import com.app.bitcointicker.data.entities.CoinList
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @Headers("Accept: application/json")
    @GET("coins/list")
    suspend fun getCurrency(@QueryMap queryMap: Map<String,String>) : Response<List<CoinList>>

    @Headers("Accept: application/json")
    @GET("coins/{id}")
    suspend fun getCurrencyDetail(@Path("id") id:String) :Response<CoinDetail>
}