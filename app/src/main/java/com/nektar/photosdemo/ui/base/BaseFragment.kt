package com.nektar.photosdemo.ui.base

import android.widget.Toast

open class BaseFragment :androidx.fragment.app.Fragment() {
    fun showToast(value: String) {
        Toast.makeText(context,value, Toast.LENGTH_LONG).show()
    }
}