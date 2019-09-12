package com.nektar.photosdemo.ui.login

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import javax.inject.Inject

class LoginViewModel @Inject constructor(): ViewModel() {
    val logged = MutableLiveData<Boolean>()
    var mail = MutableLiveData<String>()

    fun singIn(){
        logged.value = true
    }

    fun singOut(){
        logged.value = false
    }

    /**
     * Get email from received data from activity result
     */
    fun initLoginData(data: Any?){
        val task = GoogleSignIn.getSignedInAccountFromIntent(data as Intent)
        try {
            mail.value = task.getResult(ApiException::class.java)?.email
        } catch (e: Exception) {
            Log.e("Login","Google sing in error")
        }
    }

    fun initMail(value :String){
        mail.value = value
    }
}
