 package com.app.bitcointicker.ui

import android.os.Bundle
import com.app.bitcointicker.common.BaseActivity
import com.app.bitcointicker.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

 @AndroidEntryPoint
 class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)

     }
}