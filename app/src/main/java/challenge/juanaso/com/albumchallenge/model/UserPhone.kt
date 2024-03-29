package challenge.juanaso.com.albumchallenge.model

import com.google.gson.annotations.SerializedName

class UserPhone {

    @SerializedName("type")
    var typem: String? = null

    @SerializedName("number")
    var number: String? = null

    fun numberOrdered(): String {
        return typem + ": +"+ number
    }
}