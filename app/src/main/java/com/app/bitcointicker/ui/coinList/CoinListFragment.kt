package com.app.bitcointicker.ui.coinList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.bitcointicker.R
import com.app.bitcointicker.common.BaseFragment
import com.app.bitcointicker.data.entities.CoinList
import com.app.bitcointicker.databinding.FragmentCoinListBinding
import com.app.bitcointicker.ui.coinList.adapter.CoinRecyclerviewAdapter
import com.app.bitcointicker.ui.coinList.adapter.ItemClickListener
import com.app.bitcointicker.util.goneAlpha
import com.app.bitcointicker.util.visibleAlpha
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinListFragment : BaseFragment<FragmentCoinListBinding>(FragmentCoinListBinding::inflate) {

    private val viewModel : CoinListViewModel by viewModels()
    private lateinit var coinAdapter : CoinRecyclerviewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerview()
        initObservers()
        getCoinList()
    }

    private fun initObservers() {
        viewModel.coinListResponse.observe(viewLifecycleOwner,{response->
            if (!response.isNullOrEmpty()){
                coinAdapter.setList(response)
            }else{
                Snackbar.make(requireView(),"Bir hata oluştu, tekrar deneyiniz",Snackbar.LENGTH_LONG)
                    .setAction("Tekrar yükle") {
                    getCoinList()
                }.show()
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


    private fun getCoinList() {
        viewModel.getCoinList()
    }


    private fun initRecyclerview() {
        coinAdapter = CoinRecyclerviewAdapter(object : ItemClickListener{
            override fun selectedItem(data: CoinList) {
                val bundle = Bundle()
                bundle.putString("id",data.id)
                Navigation.findNavController(requireView()).navigate(R.id.action_coinListFragment_to_coinDetailFragment,bundle)
            }
        })
        binding.coinRecyclerview.apply {
            adapter = coinAdapter
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        }
    }


}