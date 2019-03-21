package challenge.juanaso.com.albumchallenge.ui.detail

import android.arch.lifecycle.MutableLiveData
import android.view.View
import challenge.juanaso.com.albumchallenge.model.Album
import challenge.juanaso.com.albumchallenge.network.RetrofitWebService
import challenge.juanaso.com.albumchallenge.viewmodel.BaseViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import challenge.juanaso.com.albumchallenge.R
import javax.inject.Inject

class PhotosViewModel : BaseViewModel() {

    @Inject
    lateinit var retrofitWebService: RetrofitWebService

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val albumToShowContent: MutableLiveData<Album> = MutableLiveData()
    val swipeToRefreshVisibility: MutableLiveData<Boolean> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    init{
        loadPosts()
    }

    fun loadPosts(){

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
        //albumAdapter.updateAlbums(albums)
    }

    private fun onRetrievePostListError(){
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

