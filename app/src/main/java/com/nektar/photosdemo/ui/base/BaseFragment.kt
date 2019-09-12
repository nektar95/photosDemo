package com.nektar.photosdemo.ui.base

import android.widget.Toast
import com.nektar.photosdemo.di.Injectable

open class BaseFragment :androidx.fragment.app.Fragment(), Injectable {
    fun showToast(value: String) {
        Toast.makeText(context,value, Toast.LENGTH_LONG).show()
    }
}