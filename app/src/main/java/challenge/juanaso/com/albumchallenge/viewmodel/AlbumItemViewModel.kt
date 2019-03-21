package challenge.juanaso.com.albumchallenge.viewmodel

import android.arch.lifecycle.MutableLiveData
import challenge.juanaso.com.albumchallenge.model.Album
import challenge.juanaso.com.albumchallenge.model.User

class AlbumItemViewModel: BaseViewModel() {
    private val title = MutableLiveData<String>()

    fun bind(album: Album){
        title.value = album.title
    }

    fun getTitle():MutableLiveData<String>{
        return title
    }
}