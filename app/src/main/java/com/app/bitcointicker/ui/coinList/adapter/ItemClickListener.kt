package com.app.bitcointicker.ui.coinList.adapter

import com.app.bitcointicker.data.entities.CoinList


interface ItemClickListener {
    fun selectedItem(data: CoinList)
}