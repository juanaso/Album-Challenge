package challenge.juanaso.com.albumchallenge.network

import challenge.juanaso.com.albumchallenge.model.Album
import challenge.juanaso.com.albumchallenge.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * The interface which provides methods to get result of webservices
 */
interface RetrofitWebService {
    /**
     * Get the list of the pots from the API
     */
    @GET("/contacts")
    fun getUsers():Observable<List<User>>

    @GET("/albums")
    fun getAlbums():Observable<List<Album>>

    @GET("/contacts/{id}")
    fun getUser(@Path("id") user: String): Observable<User>
}