package com.app.bitcointicker.ui.splash

import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.app.bitcointicker.R
import com.app.bitcointicker.common.BaseFragment
import com.app.bitcointicker.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val viewModel : SplashViewModel by viewModels()
    var androidId: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        androidId = Settings.Secure.getString(requireActivity().contentResolver, Settings.Secure.ANDROID_ID).toString()
        firebaseLogin()
        observeData()
    }

    private fun firebaseLogin() {
        viewModel.firebaseLogin(requireContext(), androidId!!)
    }

    private fun firebaseSignup(){
        viewModel.userSignup(androidId!!)
    }

    private fun observeData() {
        viewModel.isLoginSucces.observe(viewLifecycleOwner,  {
            it?.let {
                if (it) {
                  Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_coinListFragment)
                } else {
                    firebaseSignup()
                }
            }
        })

        viewModel.isSignupSucces.observe(viewLifecycleOwner, {
            it?.let {
                if (it) {
                    firebaseLogin()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Signup Bağlantı Kurulamadı, Tekrar Deneyin",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }
}