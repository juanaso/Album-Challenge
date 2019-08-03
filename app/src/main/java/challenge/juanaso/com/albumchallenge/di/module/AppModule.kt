package challenge.juanaso.com.albumchallenge.di.module

import android.content.Context
import challenge.juanaso.com.albumchallenge.AlbumChallengeAplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val albumChallengeAplication: AlbumChallengeAplication) {

    @Provides
    @Singleton
    fun provideContext(): Context = albumChallengeAplication

}