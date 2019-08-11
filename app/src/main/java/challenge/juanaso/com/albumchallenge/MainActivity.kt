package challenge.juanaso.com.albumchallenge

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import challenge.juanaso.com.albumchallenge.ui.main.MainFragment
import challenge.juanaso.com.albumchallenge.R
import challenge.juanaso.com.albumchallenge.ui.detail.DetailFragment
import challenge.juanaso.com.albumchallenge.ui.photos.PhotosFragment
import challenge.juanaso.com.albumchallenge.utils.extension.replaceFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }

    fun navigateToPhotoDetail(bundle: Bundle){
        val detailFragment = DetailFragment.newInstance()
        detailFragment.arguments = bundle
        replaceFragment(detailFragment, R.id.container)
    }

    fun navigateToPhotos(bundle: Bundle){
        val photosFragment = PhotosFragment.newInstance()
        photosFragment.arguments = bundle
        replaceFragment(photosFragment , R.id.container)
    }
}
