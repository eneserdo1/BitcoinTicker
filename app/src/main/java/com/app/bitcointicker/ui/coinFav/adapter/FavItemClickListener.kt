package com.app.bitcointicker.ui.coinFav.adapter

import com.app.bitcointicker.data.entities.CoinDetail
import com.app.bitcointicker.data.entities.CoinList


interface FavItemClickListener {
    fun selectedItem(data: CoinDetail)
}