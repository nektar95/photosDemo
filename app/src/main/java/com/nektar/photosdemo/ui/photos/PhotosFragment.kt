package com.nektar.photosdemo.ui.photos

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nektar.photosdemo.R
import com.nektar.photosdemo.di.module.FragmentModule
import com.nektar.photosdemo.di.component.DaggerFragmentComponent
import com.nektar.photosdemo.ui.base.BaseFragment
import com.nektar.photosdemo.ui.login.LoginViewModel
import kotlinx.android.synthetic.main.photo_fragment.*

class PhotosFragment : BaseFragment() {

//    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelLogin: LoginViewModel
    private lateinit var viewModel: PhotosViewModel

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
        return inflater.inflate(R.layout.photo_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelLogin = activity?.run {
            ViewModelProviders.of(this).get(LoginViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(PhotosViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        signOutBtn.setOnClickListener {
            viewModelLogin.singOut()
        }
    }
}
