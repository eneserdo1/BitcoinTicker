package com.app.bitcointicker.ui.coinFav

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.bitcointicker.R
import com.app.bitcointicker.common.BaseFragment
import com.app.bitcointicker.data.entities.CoinDetail
import com.app.bitcointicker.databinding.FragmentFavouritesCoinBinding
import com.app.bitcointicker.ui.coinFav.adapter.FavItemClickListener
import com.app.bitcointicker.ui.coinFav.adapter.FavouriteCoinRecyclerviewAdapter
import com.app.bitcointicker.util.Constants.Companion.COIN_DETAIL_ID
import com.app.bitcointicker.util.goneAlpha
import com.app.bitcointicker.util.visibleAlpha
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesCoinFragment : BaseFragment<FragmentFavouritesCoinBinding>(FragmentFavouritesCoinBinding::inflate) {

    private val viewModel : FavouritesCoinViewModel by viewModels()
    private lateinit var favAdapter:FavouriteCoinRecyclerviewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getFavCoinList()
        initObserver()
        initRecyclerview()
    }

    private fun initRecyclerview() {
        favAdapter = FavouriteCoinRecyclerviewAdapter(object :FavItemClickListener{
            override fun selectedItem(data: CoinDetail) {
                println("Selected Favourite Coin - $data")
                val bundle = Bundle()
                bundle.putString(COIN_DETAIL_ID,data.id)
                Navigation.findNavController(requireView()).navigate(R.id.action_favouritesCoinFragment_to_coinDetailFragment,bundle)
            }
        })
        binding.favCoinRecyclerview.apply {
            adapter = favAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        }
    }

    private fun initObserver() {
        viewModel.favouriteCoinList.observe(viewLifecycleOwner,{response->
            if (!response.isNullOrEmpty()){
                favAdapter.setList(response)
                viewModel.getFavorieListener()
            }else{
                binding.noFavLayout.visibleAlpha()
            }
        })

        viewModel.loading.observe(viewLifecycleOwner,{response->
            if (response){
                binding.progressbar.visibleAlpha()
            }else{
                binding.progressbar.goneAlpha()
            }
        })
    }

    private fun getFavCoinList() {
        viewModel.getFavorieList()
    }


}