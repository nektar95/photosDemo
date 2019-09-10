package com.nektar.photosdemo.di.component

import dagger.Component
import com.nektar.photosdemo.ThisApplication
import com.nektar.photosdemo.di.module.ApplicationModule

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: ThisApplication)

}