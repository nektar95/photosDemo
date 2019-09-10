package com.nektar.photosdemo.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val back = MutableLiveData<Int>()

    fun back(item: Int){
        back.value = item
    }
}
