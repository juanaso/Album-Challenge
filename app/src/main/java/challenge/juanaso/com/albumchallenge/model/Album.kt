package challenge.juanaso.com.albumchallenge.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
class Album {

    @PrimaryKey
    @NotNull
    var id:String? = null
    var userId:String? = null
    var title:String? = null
}