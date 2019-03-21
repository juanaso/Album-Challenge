package challenge.juanaso.com.albumchallenge.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.view.View
import challenge.juanaso.com.albumchallenge.model.Photo
import challenge.juanaso.com.albumchallenge.model.User
import challenge.juanaso.com.albumchallenge.network.RetrofitWebService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel(private val photo: Photo): BaseViewModel(){

    @Inject
    lateinit var retrofitWebService: RetrofitWebService

    private lateinit var subscription: Disposable

    val currentPhoto: MutableLiveData<Photo> = MutableLiveData()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val swipeToRefreshVisibility: MutableLiveData<Boolean> = MutableLiveData()
    init {
        currentPhoto.value = photo
        loadingVisibility.value = View.GONE
        swipeToRefreshVisibility.value = false
    }

    fun loadPhoto() {
        subscription = retrofitWebService.getPhoto(currentPhoto.value?.id!!)
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
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
        swipeToRefreshVisibility.value = false
    }

    private fun onRetrievePostListSuccess(photo: Photo){
        currentPhoto.value = photo
    }

    private fun onRetrievePostListError(){
        loadingVisibility.value = View.GONE
        swipeToRefreshVisibility.value = false
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}