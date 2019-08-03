package challenge.juanaso.com.albumchallenge

import android.app.Application
import challenge.juanaso.com.albumchallenge.di.component.AppComponent
import challenge.juanaso.com.albumchallenge.di.component.DaggerAppComponent
import challenge.juanaso.com.albumchallenge.di.module.AppModule
import challenge.juanaso.com.albumchallenge.di.module.NetworkModule
import challenge.juanaso.com.albumchallenge.di.module.RemoteModule
import challenge.juanaso.com.albumchallenge.di.module.RoomModule

class AlbumChallengeAplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    fun initializeDagger() {

        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .remoteModule(RemoteModule())
                .roomModule(RoomModule())
                .build()
    }
}