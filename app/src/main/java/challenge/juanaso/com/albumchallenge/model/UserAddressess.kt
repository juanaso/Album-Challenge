package challenge.juanaso.com.albumchallenge.model

import com.google.gson.annotations.SerializedName

class UserAddressess{

    @SerializedName("home")
    var home: String? = null

    @SerializedName("work")
    var work: String? = null

    fun getHomeDetailed(): String {
        return "Home: " + home
    }

    fun getWorkDetailed(): String {
        return "Work: " + work
    }
}