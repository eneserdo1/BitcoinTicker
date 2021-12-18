package com.app.bitcointicker.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.app.bitcointicker.R
import com.app.bitcointicker.common.BaseActivity
import com.app.bitcointicker.databinding.ActivitySplashBinding
import com.app.bitcointicker.ui.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    private val viewModel : SplashViewModel by viewModels()
    var androidId: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        androidId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID).toString()
        firebaseLogin()
        observeData()
    }


    private fun firebaseLogin() {
        viewModel.firebaseLogin(this, androidId!!)
    }

    private fun firebaseSignup(){
        viewModel.userSignup(androidId!!)
    }

    private fun observeData() {
        viewModel.isLoginSucces.observe(this,  {
            it?.let {
                if (it) {
                    nextActivity()
                } else {
                    firebaseSignup()
                }
            }
        })

        viewModel.isSignupSucces.observe(this, {
            it?.let {
                if (it) {
                    firebaseLogin()
                } else {
                    Toast.makeText(
                        this,
                        getString(R.string.bir_hata_olustu),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

    private fun nextActivity(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}