package com.app.bitcointicker.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.app.bitcointicker.R
import com.app.bitcointicker.common.BaseActivity
import com.app.bitcointicker.databinding.ActivityLoginBinding
import com.app.bitcointicker.ui.MainActivity
import com.app.bitcointicker.util.clickListener
import com.app.bitcointicker.util.getPref
import com.app.bitcointicker.util.goneAlpha
import com.app.bitcointicker.util.visibleAlpha
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private val viewModel : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkAuth()
        observeData()
        initListener()
        buttonsListener()
    }

    private fun checkAuth() {
        if (getPref(this).isLogin){
            nextActivity()
        }
    }

    private fun buttonsListener() {
        binding.loginButton.clickListener {
            firebaseLogin()
        }

        binding.registerButton.clickListener {
            firebaseSignup()
        }
    }


    private fun firebaseLogin() {
        viewModel.firebaseLogin(this,binding.mailEt.text.toString().trim(),binding.passwordEt.text.toString())
    }

    private fun firebaseSignup(){
        viewModel.userSignup(this, binding.mailEt.text.toString().trim(),binding.passwordEt.text.toString())
    }

    private fun observeData() {
        viewModel.isLoginSucces.observe(this,  {
            it?.let {
                if (it) {
                    nextActivity()
                } else {
                   Toast.makeText(this,getString(R.string.kayitli_kullanici_bulunamadi),Toast.LENGTH_LONG).show()
                }
            }
        })

        viewModel.isSignupSucces.observe(this, {
            it?.let {
                if (it) {
                    nextActivity()
                } else {
                    Toast.makeText(
                        this,
                        getString(R.string.bir_hata_olustu),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })

        viewModel.loading.observe(this,{response->
            if (response){
                binding.progressbar.visibleAlpha()
            }else{
                binding.progressbar.goneAlpha()
            }
        })

        lifecycleScope.launch {
            viewModel.isSubmitEnabled.collect { value->
               binding.loginButton.isEnabled = value
                binding.registerButton.isEnabled = value
            }
        }
    }

    private fun initListener() {
        binding.mailEt.addTextChangedListener {
            viewModel.setUserMail(it.toString())
        }

        binding.passwordEt.addTextChangedListener {
            viewModel.setPassword(it.toString())
        }
    }

    private fun nextActivity(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left)

    }
}