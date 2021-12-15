package com.app.bitcointicker.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseFragment<VB:ViewBinding>(
    private val bindingInflater:(LayoutInflater,ViewGroup?,Boolean)->VB):Fragment(){

    private var _binding : VB ?= null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}