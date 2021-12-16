package com.app.bitcointicker.data.entities

data class FavouriteCoin(
    val interval : Long = 0L,
    val coinDetail: CoinDetail?=null
)
