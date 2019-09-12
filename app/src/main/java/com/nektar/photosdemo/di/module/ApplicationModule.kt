package com.nektar.photosdemo.di.module

import com.nektar.photosdemo.fetching.ApiInterface
import com.nektar.photosdemo.ui.photos.model.PhotosRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

@Module(includes = [(ViewModelModule::class)])
class ApplicationModule {
    @Singleton
    @Provides
    fun providesApiInterface(): ApiInterface {
        return ApiInterface.create()
    }

}