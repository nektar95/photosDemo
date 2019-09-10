package com.nektar.photosdemo.ui.photos.model

import androidx.lifecycle.MutableLiveData
import com.nektar.photosdemo.fetching.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class PhotosRepository {
    private val api: ApiInterface = ApiInterface.create()
    /**
     * fetch list of 100 photos
     */

    fun fetchPhotos() : MutableLiveData<List<Photo>> {
        val data = MutableLiveData<List<Photo>>()
        data.value = emptyList()

        val call = api.getPhotos( Random().nextInt(8),100)
        call.enqueue(object : Callback<List<Photo>> {
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if(response.isSuccessful) {
                    data.value = response.body()
                }
            }
        })
        return data
    }
}