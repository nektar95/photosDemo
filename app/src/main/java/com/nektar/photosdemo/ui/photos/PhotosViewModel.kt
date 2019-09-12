package com.nektar.photosdemo.ui.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nektar.photosdemo.ui.photos.model.Photo
import com.nektar.photosdemo.ui.photos.model.PhotosRepository
import javax.inject.Inject

class PhotosViewModel @Inject constructor(private val photosRepository: PhotosRepository): ViewModel() {
    var items: LiveData<List<Photo>> = photosRepository.fetchPhotos()

    fun refreshPhotos(){
        items = photosRepository.fetchPhotos()
    }
}
