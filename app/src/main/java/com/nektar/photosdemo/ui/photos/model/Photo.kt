package com.nektar.photosdemo.ui.photos.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.annotations.SerializedName

data class Photo (
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("id") val id: String,
    @SerializedName("author") val author: String,
    @SerializedName("url") val url: String,
    @SerializedName("download_url") val download_url: String
) {
    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun setImageUrl(imageView: ImageView, url: String) {
            Glide.with(imageView.context)
                .load(url).apply(RequestOptions.centerCropTransform())
                .into(imageView)
        }
    }
}