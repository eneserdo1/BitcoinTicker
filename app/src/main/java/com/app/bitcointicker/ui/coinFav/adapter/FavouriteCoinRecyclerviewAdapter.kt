package com.app.bitcointicker.ui.coinFav.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.bitcointicker.data.entities.FavouriteCoin
import com.app.bitcointicker.databinding.RecyclerItemFavCoinBinding
import com.app.bitcointicker.util.clickListener

class FavouriteCoinRecyclerviewAdapter(private val itemClickListener: FavItemClickListener) :
    ListAdapter<FavouriteCoin, FavouriteCoinRecyclerviewAdapter.MyViewHolder>(FavouriteDiffCallback) {

    private lateinit var binding: RecyclerItemFavCoinBinding

    inner class MyViewHolder(val binding: RecyclerItemFavCoinBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: FavouriteCoin) = with(binding) {
            binding.coinSymbolTv.text = data.coinDetail!!.symbol
            binding.coinNameTv.text = data.coinDetail!!.name
            binding.coinPriceTv.text =
                "$${data.coinDetail.market_data.current_price.usd.toString()}"
            itemView.clickListener {
                itemClickListener.selectedItem(data.coinDetail)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding =
            RecyclerItemFavCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object FavouriteDiffCallback : DiffUtil.ItemCallback<FavouriteCoin>() {
        override fun areItemsTheSame(oldItem: FavouriteCoin, newItem: FavouriteCoin): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FavouriteCoin, newItem: FavouriteCoin): Boolean {
            return oldItem.coinDetail!!.id.toString() == newItem.coinDetail!!.id.toString()
        }
    }
}