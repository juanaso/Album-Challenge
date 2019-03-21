package challenge.juanaso.com.albumchallenge.network

import challenge.juanaso.com.albumchallenge.model.Album
import challenge.juanaso.com.albumchallenge.model.Photo
import challenge.juanaso.com.albumchallenge.model.User
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * The interface which provides methods to get result of webservices
 */
interface RetrofitWebService {
    /**
     * Get the list of the pots from the API
     */


    @GET("/albums")
    fun getAlbums():Observable<List<Album>>

    @GET("/photos")
    fun getPhotos(@Query("albumId") albumId : String):Observable<List<Photo>>

    @GET("/photos/{id}")
    fun getPhoto(@Path("id") photoId: String): Observable<Photo>
}