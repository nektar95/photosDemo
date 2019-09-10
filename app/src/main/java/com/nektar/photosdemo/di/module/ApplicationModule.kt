package com.nektar.photosdemo.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import com.nektar.photosdemo.ThisApplication
import javax.inject.Singleton


@Module
class ApplicationModule(private val baseApp: ThisApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return baseApp
    }
}