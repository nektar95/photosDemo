package com.nektar.photosdemo.di.component

import dagger.Component
import com.nektar.photosdemo.di.module.FragmentModule

@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

//    fun inject(loginFragment: LoginFragment)
//
//    fun inject(scanFragment: ScanFragment)
//
//    fun inject(inputFragment: InputFragment)

}