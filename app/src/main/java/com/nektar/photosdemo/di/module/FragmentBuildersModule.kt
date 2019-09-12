package com.nektar.photosdemo.di.module

import com.nektar.photosdemo.ui.login.LoginFragment
import com.nektar.photosdemo.ui.photos.PhotosFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    internal abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    internal abstract fun contributePhotosFragment(): PhotosFragment
}