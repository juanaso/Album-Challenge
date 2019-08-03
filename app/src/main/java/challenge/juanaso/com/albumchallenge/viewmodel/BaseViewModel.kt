package challenge.juanaso.com.albumchallenge.viewmodel

import android.arch.lifecycle.ViewModel
import challenge.juanaso.com.albumchallenge.AlbumChallengeApplication

abstract class BaseViewModel: ViewModel(){

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is MainViewModel ->   AlbumChallengeApplication.appComponent.inject(this)
            is DetailViewModel -> AlbumChallengeApplication.appComponent.inject(this)
            is PhotosViewModel -> AlbumChallengeApplication.appComponent.inject(this)
        }
    }
}