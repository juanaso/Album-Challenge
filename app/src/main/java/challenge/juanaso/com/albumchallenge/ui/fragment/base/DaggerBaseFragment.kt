package challenge.juanaso.com.albumchallenge.ui.fragment.base

import android.content.Context
import challenge.juanaso.com.albumchallenge.AlbumChallengeApplication
import dagger.android.support.AndroidSupportInjection

abstract class DaggerBaseFragment:BaseFragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AlbumChallengeApplication.appComponent.inject(this)
    }
}