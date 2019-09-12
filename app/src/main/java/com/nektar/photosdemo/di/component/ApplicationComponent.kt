package com.nektar.photosdemo.di.component

import android.app.Application
import dagger.Component
import com.nektar.photosdemo.ThisApplication
import com.nektar.photosdemo.di.module.ApplicationModule
import dagger.BindsInstance
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton
import com.nektar.photosdemo.di.module.MainActivityModule

@Singleton
@Component(modules = [AndroidInjectionModule::class, ApplicationModule::class,
    MainActivityModule::class])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(thisApplication: ThisApplication)
}