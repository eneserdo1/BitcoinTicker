 package com.app.bitcointicker.ui

import android.app.AlertDialog
import android.os.Bundle
import com.app.bitcointicker.common.BaseActivity
import com.app.bitcointicker.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import android.content.Intent
import com.app.bitcointicker.R
import com.app.bitcointicker.ui.login.LoginActivity
import com.app.bitcointicker.util.getPref


 @AndroidEntryPoint
 class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)

     }

 }