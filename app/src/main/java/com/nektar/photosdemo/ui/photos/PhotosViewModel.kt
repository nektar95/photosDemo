package com.nektar.photosdemo.ui.photos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PhotosViewModel : ViewModel() {
    val back = MutableLiveData<Int>()

    fun back(item: Int){
        back.value = item
    }
}
