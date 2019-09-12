package com.nektar.photosdemo.ui.login

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nektar.photosdemo.R
import kotlinx.android.synthetic.main.main_fragment.*
import com.nektar.photosdemo.ui.base.BaseFragment
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    private lateinit var viewModel: LoginViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory)
            .get(LoginViewModel::class.java)
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
