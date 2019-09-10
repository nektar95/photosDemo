package com.nektar.photosdemo.ui.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nektar.photosdemo.ui.photos.model.Photo
import com.nektar.photosdemo.ui.photos.model.PhotosRepository

class PhotosViewModel : ViewModel() {
    var items: LiveData<List<Photo>> = PhotosRepository().fetchPhotos()

    fun refreshPhotos(){
        items = PhotosRepository().fetchPhotos()
    }
}
