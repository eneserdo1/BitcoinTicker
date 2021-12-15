package com.app.bitcointicker.ui.coinFav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.app.bitcointicker.R
import com.app.bitcointicker.common.BaseFragment
import com.app.bitcointicker.databinding.FragmentFavouritesCoinBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesCoinFragment : BaseFragment<FragmentFavouritesCoinBinding>(FragmentFavouritesCoinBinding::inflate) {

    private val viewModel : FavouritesCoinViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}