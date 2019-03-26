package challenge.juanaso.com.albumchallenge.viewmodel

import android.arch.lifecycle.MutableLiveData
import challenge.juanaso.com.albumchallenge.model.Album
import challenge.juanaso.com.albumchallenge.model.User

class AlbumItemViewModel: BaseViewModel() {
    private val title = MutableLiveData<String>()
    private val id = MutableLiveData<String>()

    fun bind(album: Album){
        title.value = album.title
        id.value = album.id
    }

    fun getTitle():MutableLiveData<String>{
        return title
    }
    fun getId():MutableLiveData<String>{
        return id
    }
}