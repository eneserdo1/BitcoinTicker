package com.app.bitcointicker.ui.login

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.bitcointicker.util.getPref
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val auth: FirebaseAuth):ViewModel() {


    var isSignupSucces: MutableLiveData<Boolean> = MutableLiveData()
    var isLoginSucces: MutableLiveData<Boolean> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()
    private val _userMail = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    fun userSignup(context: Context,mail:String,password:String) {
        loading.postValue(true)
        auth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener{ p0 ->
                if (p0.isSuccessful) {
                    Log.i("Firebase Signup state: ", "true")
                    isSignupSucces.postValue(true)
                    loading.postValue(false)
                    getPref(context).isLogin = true
                } else {
                    Log.i("Firebase Signup state: ", p0.exception!!.message.toString())
                    isSignupSucces.postValue(false)
                    loading.postValue(false)
                }
            }

    }

    fun firebaseLogin(context: Context,mail:String,password:String) {
        loading.postValue(true)
        auth.signInWithEmailAndPassword(mail, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.i("Firebase Login state:: ", "true")
                isLoginSucces.postValue(true)
                loading.postValue(false)
                getPref(context).userId = mail
                getPref(context).isLogin = true
            } else {
                Log.i("Firebase Login state:: ", "false")
                loading.postValue(false)
                isLoginSucces.postValue(false)
            }
        }
    }


    fun setUserMail(mail:String){
        _userMail.value = mail
    }

    fun setPassword(password:String){
        _password.value = password
    }

    val isSubmitEnabled : Flow<Boolean> = combine(_userMail,_password){ userMail, password->
        val isMailCorrect = Patterns.EMAIL_ADDRESS.matcher(userMail).matches()
        val isPasswordCorrect = password.length > 4
        return@combine isMailCorrect and isPasswordCorrect

    }

}