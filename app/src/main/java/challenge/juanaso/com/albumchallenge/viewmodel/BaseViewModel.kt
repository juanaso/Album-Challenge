package challenge.juanaso.com.albumchallenge.viewmodel

import android.arch.lifecycle.ViewModel
import challenge.juanaso.com.albumchallenge.AlbumChallengeAplication
import challenge.juanaso.com.albumchallenge.di.component.AppComponent
import challenge.juanaso.com.albumchallenge.di.module.NetworkModule

abstract class BaseViewModel: ViewModel(){

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is MainViewModel ->   AlbumChallengeAplication.appComponent.inject(this)
            is DetailViewModel -> AlbumChallengeAplication.appComponent.inject(this)
            is PhotosViewModel -> AlbumChallengeAplication.appComponent.inject(this)
        }
    }
}