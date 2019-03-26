package challenge.juanaso.com.albumchallenge.viewmodel

import android.arch.lifecycle.MutableLiveData
import challenge.juanaso.com.albumchallenge.model.Photo

class PhotoItemViewModel {
    private val title = MutableLiveData<String>()
    private val thumbnailUrl = MutableLiveData<String>()
    private val id = MutableLiveData<String>()

    fun bind(photo: Photo){
        title.value = photo.title
        thumbnailUrl.value = photo.thumbnailUrl
        id.value = photo.id
    }

    fun getTitle():MutableLiveData<String>{
        return title
    }

    fun getThumbnailUrl():MutableLiveData<String>{
        return thumbnailUrl
    }

    fun getId():MutableLiveData<String>{
        return id
    }
}