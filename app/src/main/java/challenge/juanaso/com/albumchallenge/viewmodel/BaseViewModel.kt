package challenge.juanaso.com.albumchallenge.viewmodel

import android.arch.lifecycle.ViewModel
import challenge.juanaso.com.albumchallenge.di.component.DaggerViewModelInjector
import challenge.juanaso.com.albumchallenge.di.component.ViewModelInjector
import challenge.juanaso.com.albumchallenge.di.module.NetworkModule

abstract class BaseViewModel: ViewModel(){


    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()
    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is MainViewModel -> injector.inject(this)
            is DetailViewModel -> injector.inject(this)
            is PhotosViewModel -> injector.inject(this)
        }
    }
}