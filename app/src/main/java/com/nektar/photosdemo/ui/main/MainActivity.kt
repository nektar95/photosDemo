package com.nektar.photosdemo.ui.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.nektar.photosdemo.R
import com.nektar.photosdemo.di.component.DaggerActivityComponent
import com.nektar.photosdemo.di.module.ActivityModule
import com.nektar.photosdemo.ui.login.LoginViewModel
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    /**
     * stroring google stuff here, becouse dont want to store activity in viewmodel
     */

    val GOOGLE_REQ_CODE : Int = 418
    val gso =  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()
    lateinit var googleSignInClient : GoogleSignInClient

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        injectDependency()

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        checkLogin()

        /**
         * init viewmodel to observe login changes
         */
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        viewModel.logged.observe(this,Observer<Boolean>{
            if(it){
                val signInIntent = googleSignInClient.signInIntent
                try {
                    startActivityForResult( signInIntent, GOOGLE_REQ_CODE, null)
                } catch (e : Exception){

                }
            } else{
                googleSignInClient.signOut()
                viewModel.initMail("")
                openLogin()
            }
        })
    }

    private fun checkLogin(){
        if(isUserLoggedIn()){
            val acct = GoogleSignIn.getLastSignedInAccount(this)
            if (acct != null) {
                acct.email?.let { viewModel.initMail(it) }
            }
        }
    }

    private fun openLogin(){
        findNavController(R.id.nav_host_fragment).navigate(R.id.loginFragment)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GOOGLE_REQ_CODE && resultCode != Activity.RESULT_CANCELED){
            viewModel.initLoginData(data)
        }
    }

    fun isUserLoggedIn() : Boolean {
        val googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this)
        return  googleSignInAccount != null
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }
}
