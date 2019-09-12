package com.nektar.photosdemo.ui.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.nektar.photosdemo.R
import com.nektar.photosdemo.di.Injectable
import com.nektar.photosdemo.ui.login.LoginViewModel
import dagger.android.support.HasSupportFragmentInjector
import java.lang.Exception
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, Injectable {

    /**
     * stroring google stuff here, becouse dont want to store activity in viewmodel
     */

    val GOOGLE_REQ_CODE : Int = 418
    val gso =  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()
    lateinit var googleSignInClient : GoogleSignInClient

    private lateinit var viewModel: LoginViewModel

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(LoginViewModel::class.java)

        checkLogin()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        /**
         * init viewmodel to observe login changes
         */

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
        findNavController(R.id.nav_host_fragment).navigate(R.id.action_photosFragment_to_loginFragment2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GOOGLE_REQ_CODE && resultCode != Activity.RESULT_CANCELED){
            viewModel.initLoginData(data)
        }
    }

    private fun isUserLoggedIn() : Boolean {
        val googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this)
        return  googleSignInAccount != null
    }
}
