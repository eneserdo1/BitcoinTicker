package com.app.bitcointicker.ui.coinFav.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.bitcointicker.data.entities.FavouriteCoin
import com.app.bitcointicker.databinding.RecyclerItemFavCoinBinding
import com.app.bitcointicker.util.clickListener

class FavouriteCoinRecyclerviewAdapter(private val itemClickListener: FavItemClickListener) : RecyclerView.Adapter<FavouriteCoinRecyclerviewAdapter.MyHolder>() {

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
            binding.favouriteItemLayout.clickListener {
                itemClickListener.selectedItem(data.coinDetail)
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