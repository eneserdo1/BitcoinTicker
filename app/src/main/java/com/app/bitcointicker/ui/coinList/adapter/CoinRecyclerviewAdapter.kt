package com.app.bitcointicker.ui.coinList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.bitcointicker.data.entities.CoinList
import com.app.bitcointicker.databinding.RecyclerItemCoinBinding
import com.app.bitcointicker.util.clickListener

class CoinRecyclerviewAdapter(private val clickListener: ItemClickListener) : RecyclerView.Adapter<CoinRecyclerviewAdapter.MyHolder>() {

    var originalList: MutableList<CoinList> = arrayListOf()
    var filterList: MutableList<CoinList> = arrayListOf()
    lateinit var binding: RecyclerItemCoinBinding


    fun setList(newList: List<CoinList>) {
        this.originalList = newList as MutableList<CoinList>
        this.filterList = newList
        notifyDataSetChanged()
    }

    inner class MyHolder(private val binding: RecyclerItemCoinBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CoinList) = with(binding) {
            binding.coinSymbolTv.text = data.symbol
            binding.coinNameTv.text = data.name
            itemView.clickListener {
                clickListener.selectedItem(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        binding = RecyclerItemCoinBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(this.filterList[position])
    }

    override fun getItemCount(): Int {
        return this.filterList.size
    }


}