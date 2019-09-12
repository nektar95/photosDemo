package com.nektar.photosdemo.ui.photos

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nektar.photosdemo.R
import com.nektar.photosdemo.databinding.PhotoFragmentBinding
import com.nektar.photosdemo.ui.base.BaseFragment
import com.nektar.photosdemo.ui.login.LoginViewModel
import com.nektar.photosdemo.ui.photos.model.Photo
import com.nektar.photosdemo.ui.photos.recyclerview.PhotoAdapter
import kotlinx.android.synthetic.main.photo_fragment.*
import javax.inject.Inject

class PhotosFragment : BaseFragment() {

    private lateinit var viewModelLogin: LoginViewModel
    private lateinit var viewModel: PhotosViewModel
    private lateinit var binding: PhotoFragmentBinding

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

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

        viewModel = ViewModelProviders.of(activity!!, viewModelFactory)
            .get(PhotosViewModel::class.java)

        viewModelLogin = ViewModelProviders.of(activity!!, viewModelFactory)
            .get(LoginViewModel::class.java)

        val layoutManager = LinearLayoutManager(context)

        signOutBtn.setOnClickListener {
            viewModelLogin.singOut()
        }

        recyclerview.layoutManager = layoutManager
        recyclerview.hasFixedSize()
        recyclerview.adapter = PhotoAdapter()

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshPhotos()
            swipeRefreshLayout.isRefreshing = false
            viewModel.items.removeObservers(this)
            observePhotos()
        }

        binding.viewModel = viewModel
        observePhotos()
    }

    private fun observePhotos(){
        viewModel.items.observe(this, Observer<List<Photo>>{
            (recyclerview.adapter as PhotoAdapter).update(it)
        })
    }
}
