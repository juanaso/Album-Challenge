package challenge.juanaso.com.albumchallenge.ui.fragment.base

import android.arch.lifecycle.ViewModel


abstract class MvvmBaseFragment<vm : ViewModel> : BaseFragment() {
    abstract fun getViewModel(): vm
}
