package challenge.juanaso.com.albumchallenge.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import challenge.juanaso.com.albumchallenge.model.Album

@Dao
interface AlbumDao {
    @get:Query("SELECT * FROM album")
    val all: List<Album>

    @Insert
    fun insertAll(album: List<Album>)

    @Query("DELETE FROM album")
    fun deleteAllAlbums()
}