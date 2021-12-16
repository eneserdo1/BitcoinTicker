package com.app.bitcointicker.ui.coinDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import coil.load
import com.app.bitcointicker.common.BaseFragment
import com.app.bitcointicker.data.entities.CoinDetail
import com.app.bitcointicker.data.entities.FavouriteCoin
import com.app.bitcointicker.databinding.FragmentCoinDetailBinding
import com.app.bitcointicker.util.clickListener
import com.app.bitcointicker.util.goneAlpha
import com.app.bitcointicker.util.visibleAlpha
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_coin_detail.*


@AndroidEntryPoint
class CoinDetailFragment : BaseFragment<FragmentCoinDetailBinding>(FragmentCoinDetailBinding::inflate) {

    private val viewModel: CoinDetailViewModel by viewModels()
    private var id:String?=null
    private var currentCoin : CoinDetail?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id = arguments?.getString("id")
        initObservers()
        getCoinDetail()
        buttonsListener()
    }

    private fun buttonsListener() {
        binding.addFavButton.clickListener {
            currentCoin?.let {coin->
                val interval = binding.coinTimer.text.toString().toLong()
                interval.let {time->
                    viewModel.addFirestore(FavouriteCoin(time,coin))
                }
            }
        }
    }

    private fun initObservers() {
        viewModel.coinDetailResponse.observe(viewLifecycleOwner,{response->
            if (response != null){
                setValues(response)
                currentCoin = response
            }else{
                currentCoin = null
                Snackbar.make(requireView(),"Bir hata oluştu, tekrar deneyiniz", Snackbar.LENGTH_LONG)
                    .setAction("Tekrar yükle") {
                        getCoinDetail()
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

        viewModel.addFirestoreState.observe(viewLifecycleOwner,{response->
            if (response){
               binding.coinTimer.text.clear()
            }
        })

    }

    private fun setValues(data:CoinDetail) {
        binding.apply {
            coin_name_tv.text = data.name
            coinCurrentPriceTv.text = data.market_data.current_price.usd.toString()
            coinChangePercentageTv.text = data.market_data.price_change_percentage_24h.toString()
            coinHashAlgorithmTv.text = data.hashing_algorithm
            coin_imageview.load(data.image!!.large)
            coin_description_tv.text = data.description!!.en
        }
    }

    private fun getCoinDetail() {
        id?.let {
            viewModel.getCoinDetail(it)
        }
    }
}