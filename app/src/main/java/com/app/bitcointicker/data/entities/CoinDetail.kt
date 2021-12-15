package com.app.bitcointicker.data.entities
import com.google.gson.annotations.SerializedName

data class CoinDetail (

	@field:SerializedName("id") val id : String?=null,
	@field:SerializedName("symbol") val symbol : String?=null,
	@field:SerializedName("name") val name : String?=null,
	@field:SerializedName("image") val image : String?=null,
	@field:SerializedName("current_price") val current_price : String?=null,
	@field:SerializedName("market_cap") val market_cap : String?=null,
	@field:SerializedName("market_cap_rank") val market_cap_rank : Int?=null,
	@field:SerializedName("fully_diluted_valuation") val fully_diluted_valuation : String?=null,
	@field:SerializedName("total_volume") val total_volume : String?=null,
	@field:SerializedName("high_24h") val high_24h : String?=null,
	@field:SerializedName("low_24h") val low_24h : String?=null,
	@field:SerializedName("price_change_24h") val price_change_24h : String?=null,
	@field:SerializedName("price_change_percentage_24h") val price_change_percentage_24h : String?=null,
	@field:SerializedName("market_cap_change_24h") val market_cap_change_24h : String?=null,
	@field:SerializedName("market_cap_change_percentage_24h") val market_cap_change_percentage_24h : Double?=null,
	@field:SerializedName("circulating_supply") val circulating_supply : String?=null,
	@field:SerializedName("total_supply") val total_supply : String?=null,
	@field:SerializedName("max_supply") val max_supply : String?=null,
	@field:SerializedName("ath") val ath : Double?=null,
	@field:SerializedName("ath_change_percentage") val ath_change_percentage : String?=null,
	@field:SerializedName("ath_date") val ath_date : String?=null,
	@field:SerializedName("atl") val atl : Double?=null,
	@field:SerializedName("atl_change_percentage") val atl_change_percentage : String?=null,
	@field:SerializedName("atl_date") val atl_date : String?=null,
	@field:SerializedName("roi") val roi : Roi?=null,
	@field:SerializedName("last_updated") val last_updated : String?=null
)

