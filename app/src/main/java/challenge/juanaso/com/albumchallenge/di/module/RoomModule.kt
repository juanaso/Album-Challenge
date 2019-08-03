package challenge.juanaso.com.albumchallenge.di.module

import android.content.Context
import challenge.juanaso.com.albumchallenge.persistence.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context) = AppDatabase.getInstance(context)
}