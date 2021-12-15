package com.app.bitcointicker.ui.coinDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.app.bitcointicker.common.BaseFragment
import com.app.bitcointicker.databinding.FragmentCoinDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CoinDetailFragment : BaseFragment<FragmentCoinDetailBinding>(FragmentCoinDetailBinding::inflate) {

    private val viewModel: CoinDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}