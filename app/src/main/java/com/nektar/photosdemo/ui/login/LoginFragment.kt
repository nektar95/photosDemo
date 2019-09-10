package com.nektar.photosdemo.ui.login

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nektar.photosdemo.R
import com.nektar.photosdemo.di.module.FragmentModule
import com.nektar.photosdemo.ui.main.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*
import com.nektar.photosdemo.di.component.DaggerFragmentComponent

class LoginFragment : androidx.fragment.app.Fragment() {

    fun newInstance(): LoginFragment {
        return LoginFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()

    }

    fun showToast(value: String) {
        Toast.makeText(context,value,Toast.LENGTH_LONG).show()
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
            ViewModelProviders.of(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }
}
