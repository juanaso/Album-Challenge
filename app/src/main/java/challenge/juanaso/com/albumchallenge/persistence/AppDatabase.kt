package challenge.juanaso.com.albumchallenge.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import challenge.juanaso.com.albumchallenge.model.Album
import challenge.juanaso.com.albumchallenge.model.User


@Database(entities = arrayOf(User::class,Album::class), version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun albumDao(): AlbumDao
}