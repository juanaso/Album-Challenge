package challenge.juanaso.com.albumchallenge.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.support.v4.app.FragmentActivity
import challenge.juanaso.com.albumchallenge.persistence.AppDatabase
import challenge.juanaso.com.albumchallenge.viewmodel.MainViewModel

class ViewModelFactory(private val activity: FragmentActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
//            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "userdatabase").build()
//            @Suppress("UNCHECKED_CAST")
//            return MainViewModel(db) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//
//    }
}