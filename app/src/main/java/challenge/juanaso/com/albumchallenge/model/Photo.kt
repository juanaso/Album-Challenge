package challenge.juanaso.com.albumchallenge.model

import android.os.Parcel
import android.os.Parcelable

class Photo() : Parcelable {

    var id:String? = null
    var albumId:String? = null
    var title:String? = null
    var url:String? = null
    var thumbnailUrl:String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        albumId = parcel.readString()
        title = parcel.readString()
        url = parcel.readString()
        thumbnailUrl = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(albumId)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(thumbnailUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }
}