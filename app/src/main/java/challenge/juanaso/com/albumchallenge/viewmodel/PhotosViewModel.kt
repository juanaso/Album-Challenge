package challenge.juanaso.com.albumchallenge.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.view.View
import challenge.juanaso.com.albumchallenge.network.RetrofitWebService
import io.reactivex.disposables.Disposable
import challenge.juanaso.com.albumchallenge.R
import challenge.juanaso.com.albumchallenge.model.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PhotosViewModel(private val albumId :String): BaseViewModel() {

    @Inject
    lateinit var retrofitWebService: RetrofitWebService

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val PhotoToShowDetail: MutableLiveData<Photo> = MutableLiveData()
    val swipeToRefreshVisibility: MutableLiveData<Boolean> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    init{
        loadPosts()
    }

    fun loadPosts(){
        subscription = retrofitWebService.getPhotos(albumId)
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

    private fun onRetrievePostListSuccess(photo:List<Photo>){
        //albumAdapter.updateAlbums(albums)
    }

    private fun onRetrievePostListError(){
        errorMessage.value = R.string.post_error

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun onItemClick(photo: Photo){
        PhotoToShowDetail.value = photo
    }
}

