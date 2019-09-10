package com.nektar.photosdemo.ui.photos

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nektar.photosdemo.R
import com.nektar.photosdemo.databinding.PhotoFragmentBinding
import com.nektar.photosdemo.di.module.FragmentModule
import com.nektar.photosdemo.di.component.DaggerFragmentComponent
import com.nektar.photosdemo.ui.base.BaseFragment
import com.nektar.photosdemo.ui.login.LoginViewModel
import com.nektar.photosdemo.ui.photos.model.Photo
import com.nektar.photosdemo.ui.photos.recyclerview.PhotoAdapter
import kotlinx.android.synthetic.main.photo_fragment.*

class PhotosFragment : BaseFragment() {

    private lateinit var viewModelLogin: LoginViewModel
    private lateinit var viewModel: PhotosViewModel
    private lateinit var binding: PhotoFragmentBinding

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
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.photo_fragment, container, false)
        binding.setLifecycleOwner(this)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelLogin = activity?.run {
            ViewModelProviders.of(this).get(LoginViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(PhotosViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        val layoutManager = LinearLayoutManager(context)

        signOutBtn.setOnClickListener {
            viewModelLogin.singOut()
        }

        recyclerview.layoutManager = layoutManager
        recyclerview.hasFixedSize()
        recyclerview.adapter = PhotoAdapter()
        recyclerview.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshPhotos()
            swipeRefreshLayout.isRefreshing = false
            viewModel.items.removeObservers(this)
            observePhotos()
        }

        binding.viewModel = viewModel
        viewModel.items.observe(this, Observer<List<Photo>>{
            (recyclerview.adapter as PhotoAdapter).update(it)
        })
        observePhotos()
    }

    private fun observePhotos(){
        viewModel.items.observe(this, Observer<List<Photo>>{
            (recyclerview.adapter as PhotoAdapter).update(it)
        })
    }
}
