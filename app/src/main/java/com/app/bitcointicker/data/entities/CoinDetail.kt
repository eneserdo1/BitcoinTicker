package com.app.bitcointicker.data.entities
import com.google.gson.annotations.SerializedName

data class CoinDetail (

	@field:SerializedName("description") val description: Description?,
	@field:SerializedName("hashing_algorithm") val hashing_algorithm: String?,
	@field:SerializedName("id") val id: String?,
	@field:SerializedName("image") val image: Image?,
	@field:SerializedName("name") val name: String?,
	@field:SerializedName("symbol") val symbol: String?,
	@field:SerializedName("market_data") val market_data: MarketData
){
	data class Description(
		@field:SerializedName("ar") val ar: String?,
		@field:SerializedName("de") val de: String?,
		@field:SerializedName("en") val en: String?,
		@field:SerializedName("es") val es: String?,
		@field:SerializedName("fr") val fr: String?,
		@field:SerializedName("hu") val hu: String?,
		@field:SerializedName("id") val id: String?,
		@field:SerializedName("ja") val ja: String?,
		@field:SerializedName("ko") val ko: String?,
		@field:SerializedName("nl") val nl: String?,
		@field:SerializedName("pl") val pl: String?,
		@field:SerializedName("pt") val pt: String?,
		@field:SerializedName("ro") val ro: String?,
		@field:SerializedName("ru") val ru: String?,
		@field:SerializedName("sv") val sv: String?,
		@field:SerializedName("th") val th: String?,
		@field:SerializedName("tr") val tr: String?,
		@field:SerializedName("vi") val vi: String?
	)

	data class Image(
		@field:SerializedName("large") val large: String?,
		@field:SerializedName("small") val small: String?,
		@field:SerializedName("thumb") val thumb: String?
	)

	data class MarketData(
		@field:SerializedName("current_price") val current_price: CurrentPrice,
		@field:SerializedName("price_change_percentage_24h") val price_change_percentage_24h:Double?
	)

	data class CurrentPrice(
		@field:SerializedName("usd") val usd: Double?
	)
}

