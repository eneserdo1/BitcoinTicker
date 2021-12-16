package com.app.bitcointicker.ui.splash

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.bitcointicker.util.getPref
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor():ViewModel() {

    var auth = FirebaseAuth.getInstance()
    var isSignupSucces: MutableLiveData<Boolean> = MutableLiveData()
    var isLoginSucces: MutableLiveData<Boolean> = MutableLiveData()


    fun userSignup(uuid: String) {
        auth.createUserWithEmailAndPassword("$uuid@bitcoin.com",uuid).addOnCompleteListener {
            OnCompleteListener<AuthResult> { p0 ->
                if (p0.isSuccessful) {
                    Log.i("Firebase Signup state: ", "true")
                    isSignupSucces.postValue(true)
                } else {
                    Log.i("Firebase Signup state: ", "false")
                    isSignupSucces.postValue(false)
                }
            }
        }
    }

    fun firebaseLogin(context: Context, uuid: String) {
        auth.signInWithEmailAndPassword("$uuid@bitcoin.com", uuid).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.i("Firebase Login state:: ", "true")
                isLoginSucces.postValue(true)
                getPref(context).userId = uuid
            } else {
                Log.i("Firebase Login state:: ", "false")
                isLoginSucces.postValue(false)
            }
        }
    }

}