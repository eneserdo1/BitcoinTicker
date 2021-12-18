package com.app.bitcointicker.ui.coinFav.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.app.bitcointicker.data.entities.CoinDetail
import com.app.bitcointicker.data.entities.CoinList
import com.app.bitcointicker.data.entities.FavouriteCoin
import com.app.bitcointicker.databinding.RecyclerItemCoinBinding
import com.app.bitcointicker.databinding.RecyclerItemFavCoinBinding
import com.app.bitcointicker.util.clickListener

class FavouriteCoinRecyclerviewAdapter(private val clickListener: FavItemClickListener) : RecyclerView.Adapter<FavouriteCoinRecyclerviewAdapter.MyHolder>() {

    var originalList: MutableList<FavouriteCoin> = arrayListOf()
    lateinit var binding: RecyclerItemFavCoinBinding


    fun setList(newList: List<FavouriteCoin>) {
        this.originalList = newList as MutableList<FavouriteCoin>
        notifyDataSetChanged()
    }

    inner class MyHolder(private val binding: RecyclerItemFavCoinBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: FavouriteCoin) = with(binding) {
            binding.coinSymbolTv.text = data.coinDetail!!.symbol
            binding.coinNameTv.text = data.coinDetail!!.name
            binding.coinPriceTv.text = "$${data.coinDetail.market_data.current_price.usd.toString()}"
            itemView.clickListener {
                clickListener.selectedItem(data.coinDetail!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        binding = RecyclerItemFavCoinBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(this.originalList[position])
    }

    override fun getItemCount(): Int {
        return this.originalList.size
    }



}