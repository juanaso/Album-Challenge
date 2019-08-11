package challenge.juanaso.com.albumchallenge.di.module

import android.content.Context
import challenge.juanaso.com.albumchallenge.AlbumChallengeApplication
import challenge.juanaso.com.albumchallenge.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainFragmentModule() {

    @Provides
    @Singleton
    fun provideMainViewModel(): MainViewModel = MainViewModel()

}