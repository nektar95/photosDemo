package com.nektar.photosdemo.ui.photos.model

import com.google.gson.annotations.SerializedName

data class Photo (
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("id") val id: String,
    @SerializedName("author") val author: String,
    @SerializedName("url") val url: String,
    @SerializedName("download_url") val download_url: String
)