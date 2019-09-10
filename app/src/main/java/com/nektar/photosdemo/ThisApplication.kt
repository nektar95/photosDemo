package com.nektar.photosdemo

import android.app.Application
import com.nektar.photosdemo.di.component.ApplicationComponent
import com.nektar.photosdemo.di.component.DaggerApplicationComponent
import com.nektar.photosdemo.di.module.ApplicationModule

class ThisApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        setup()
    }

    fun setup() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: ThisApplication private set
    }
}