package com.nektar.photosdemo.fetching

import com.google.gson.GsonBuilder
import com.nektar.photosdemo.BuildConfig
import com.nektar.photosdemo.ui.photos.model.Photo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {
    @GET
    @Headers("Content-Type: application/json")
    fun getPhotos(): Call<List<Photo>>

    companion object Factory {
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .baseUrl(BuildConfig.API_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}