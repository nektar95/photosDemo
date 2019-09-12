package com.nektar.photosdemo.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.nektar.photosdemo.di.ViewModelKey;
import com.nektar.photosdemo.ui.login.LoginViewModel;
import com.nektar.photosdemo.ui.photos.PhotosViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract public class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoignViewModel(LoginViewModel loginViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PhotosViewModel.class)
    abstract ViewModel bindPhotosViewModel(PhotosViewModel photosViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
