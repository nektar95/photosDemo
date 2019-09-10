@file:JvmName("PhotoAdapter")

package com.nektar.photosdemo.ui.photos.recyclerview

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nektar.photosdemo.ui.photos.model.Photo

class PhotoAdapter  : RecyclerView.Adapter<PhotoHolder>() {
    var items : List<Photo> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        return PhotoHolder(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.bind(items.get(position))
    }

    fun update(photoList: List<Photo>) {
        this.items = photoList
        notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("items")
        fun RecyclerView.bindItems(items: List<Photo>) {
            val adapter = adapter as PhotoAdapter
            adapter.update(items)
        }
    }
}