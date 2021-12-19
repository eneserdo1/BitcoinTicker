package com.app.bitcointicker.ui.coinList.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.app.bitcointicker.data.entities.CoinList
import com.app.bitcointicker.databinding.RecyclerItemCoinBinding
import com.app.bitcointicker.util.clickListener

class CoinRecyclerviewAdapter(private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<CoinRecyclerviewAdapter.MyHolder>(),Filterable {

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
                itemClickListener.selectedItem(data)
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filterList = originalList
                } else {
                    val resultList = ArrayList<CoinList>()
                    for (row in originalList) {
                        if (row.name!!.toLowerCase().contains(charSearch.toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    filterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<CoinList>
                notifyDataSetChanged()
            }

        }
    }

}