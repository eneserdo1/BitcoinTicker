package com.app.bitcointicker.data.entities

import com.google.gson.annotations.SerializedName

data class Roi(
    @field:SerializedName("times") val time: String?=null,
    @field:SerializedName("currency") val currency: String?=null,
    @field:SerializedName("percentage") val percentage: String?=null,
)
