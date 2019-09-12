package com.nektar.photosdemo.ui.photos.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nektar.photosdemo.fetching.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class PhotosRepository @Inject constructor(private val apiInterface: ApiInterface){
    /**
     * fetch list of 100 photos
     */

    fun fetchPhotos() : MutableLiveData<List<Photo>> {
        val data = MutableLiveData<List<Photo>>()
        data.value = emptyList()

        val call = apiInterface.getPhotos( Random().nextInt(8),100)
        call.enqueue(object : Callback<List<Photo>> {
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Log.e("API","Failure" + t.message)
            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if(response.isSuccessful) {
                    data.value = response.body()
                } else{
                    Log.e("API","Code" + response.code())
                }
            }
        })
        return data
    }
}