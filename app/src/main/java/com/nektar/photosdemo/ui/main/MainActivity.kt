package com.nektar.photosdemo.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.nektar.photosdemo.R
import com.nektar.photosdemo.di.component.DaggerActivityComponent
import com.nektar.photosdemo.di.module.ActivityModule
import com.nektar.photosdemo.ui.login.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        injectDependency()

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        showLoginFragment()
    }

    fun showLoginFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, LoginFragment().newInstance())
            .setCustomAnimations(R.anim.fade_out, R.anim.fade_in)
            .commitNow()
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }
}
