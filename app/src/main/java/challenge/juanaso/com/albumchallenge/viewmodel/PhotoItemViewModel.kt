package challenge.juanaso.com.albumchallenge.viewmodel

import android.arch.lifecycle.MutableLiveData
import challenge.juanaso.com.albumchallenge.model.Photo

class PhotoItemViewModel {
    private val title = MutableLiveData<String>()
    private val thumbnailUrl = MutableLiveData<String>()

    fun bind(photo: Photo){
        title.value = photo.title
        thumbnailUrl.value = photo.thumbnailUrl

    }

    fun getTitle():MutableLiveData<String>{
        return title
    }

    fun getThumbnailUrl():MutableLiveData<String>{
        return thumbnailUrl
    }



}