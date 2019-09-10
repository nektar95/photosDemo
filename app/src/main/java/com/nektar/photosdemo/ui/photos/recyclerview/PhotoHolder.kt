package com.nektar.photosdemo.ui.photos.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nektar.photosdemo.R
import com.nektar.photosdemo.databinding.PhotoItemBinding
import com.nektar.photosdemo.ui.photos.model.Photo

class PhotoHolder(private val parent: ViewGroup,
                  private val binding: PhotoItemBinding = DataBindingUtil.inflate(
                      LayoutInflater.from(parent.context),
                      R.layout.photo_item,
                      parent,
                      false
                  )
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Photo) {
        binding.photo = item
        binding.executePendingBindings()
    }
}