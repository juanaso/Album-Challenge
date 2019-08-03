package challenge.juanaso.com.albumchallenge.di.component

import challenge.juanaso.com.albumchallenge.MainActivity
import challenge.juanaso.com.albumchallenge.di.module.AppModule
import challenge.juanaso.com.albumchallenge.di.module.NetworkModule
import challenge.juanaso.com.albumchallenge.di.module.RemoteModule
import challenge.juanaso.com.albumchallenge.di.module.RoomModule
import challenge.juanaso.com.albumchallenge.ui.main.MainFragment
import challenge.juanaso.com.albumchallenge.viewmodel.BaseViewModel
import challenge.juanaso.com.albumchallenge.viewmodel.DetailViewModel
import challenge.juanaso.com.albumchallenge.viewmodel.MainViewModel
import challenge.juanaso.com.albumchallenge.viewmodel.PhotosViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, RoomModule::class, RemoteModule::class))
interface AppComponent {
     /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param postListViewModel PostListViewModel in which to inject the dependencies
     */

    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
    
    fun inject(baseViewModel: BaseViewModel)
    fun inject(mainViewModel: MainViewModel)
    fun inject(detailViewModel: DetailViewModel)
    fun inject(photosViewModel: PhotosViewModel)

}