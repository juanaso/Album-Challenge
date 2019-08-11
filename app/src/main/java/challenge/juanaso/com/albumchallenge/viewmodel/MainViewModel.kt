package challenge.juanaso.com.albumchallenge.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.view.View
import challenge.juanaso.com.albumchallenge.R
import challenge.juanaso.com.albumchallenge.model.Album

import challenge.juanaso.com.albumchallenge.model.User
import challenge.juanaso.com.albumchallenge.network.RetrofitWebService
import challenge.juanaso.com.albumchallenge.persistence.AppDatabase
import challenge.juanaso.com.albumchallenge.ui.main.AlbumAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainViewModel @Inject constructor(): BaseViewModel() {

    @Inject
    lateinit var retrofitWebService: RetrofitWebService

    @Inject
    lateinit var dataBase: AppDatabase

    private lateinit var subscription: Disposable

    val albumAdapter: AlbumAdapter = AlbumAdapter( { album : Album -> onItemClick(album) })
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val albumToShowContent:MutableLiveData<Album> = MutableLiveData()
    val swipeToRefreshVisibility: MutableLiveData<Boolean> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    init{
        loadPosts()
    }

    fun loadPosts(){
        var albumDao = dataBase.albumDao()
        subscription = Observable.fromCallable { albumDao.all }
                .concatMap {
                    dbAlbumList ->
                    if(dbAlbumList.isEmpty())
                        retrofitWebService.getAlbums().concatMap {
                            apiAlbum-> albumDao.insertAll(apiAlbum)
                            Observable.just(apiAlbum)
                        }
                    else
                        Observable.just(dbAlbumList)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(
                        { result -> onRetrievePostListSuccess(result) },
                        { onRetrievePostListError() }
                )
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        swipeToRefreshVisibility.value = true
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
        swipeToRefreshVisibility.value = false
    }

    private fun onRetrievePostListSuccess(albums:List<Album>){
        albumAdapter.updateAlbums(albums)
    }

    private fun onRetrievePostListError(){
        if(albumAdapter.itemCount<1)
        errorMessage.value = R.string.post_error

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun onItemClick(album: Album){
        albumToShowContent.value = album
    }
}

