package com.nektar.photosdemo.ui.login

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nektar.photosdemo.R
import com.nektar.photosdemo.di.module.FragmentModule
import kotlinx.android.synthetic.main.main_fragment.*
import com.nektar.photosdemo.di.component.DaggerFragmentComponent
import com.nektar.photosdemo.ui.base.BaseFragment

class LoginFragment : BaseFragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    private fun injectDependency() {
        val aboutComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

//        aboutComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(LoginViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        button_login.setOnClickListener {
            viewModel.singIn()
        }
        viewModel.mail.observe(this, Observer<String> {
            //quick way to check if logged in
            if(it.isNotEmpty()) {
                findNavController().navigate(R.id.action_loginFragment_to_photosFragment)
            }
        })
    }
}
