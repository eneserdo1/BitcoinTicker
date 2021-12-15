package com.app.bitcointicker.data.entities

import com.google.gson.annotations.SerializedName


data class CoinList(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("symbol")
    val symbol: String? = null,
    @SerializedName("name")
    val name: String? = null,
)