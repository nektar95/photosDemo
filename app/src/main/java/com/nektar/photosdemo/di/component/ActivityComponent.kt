package com.nektar.photosdemo.di.component

import dagger.Component
import com.nektar.photosdemo.ui.main.MainActivity
import com.nektar.photosdemo.di.module.ActivityModule

@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}