package challenge.juanaso.com.albumchallenge.ui.photos

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import challenge.juanaso.com.albumchallenge.MainActivity
import challenge.juanaso.com.albumchallenge.databinding.FragmentPhotosBinding
import challenge.juanaso.com.albumchallenge.R
import challenge.juanaso.com.albumchallenge.utils.ALBUM_ID
import challenge.juanaso.com.albumchallenge.utils.PHOTO
import challenge.juanaso.com.albumchallenge.viewmodel.PhotosViewModel
import kotlinx.android.synthetic.main.fragment_photos.*
import kotlinx.android.synthetic.main.main_fragment.*

class PhotosFragment : Fragment() {
    private var albumId: String? = null
    private lateinit var binding: FragmentPhotosBinding
    private var errorSnackbar: Snackbar? = null

    private lateinit var viewModel: PhotosViewModel
    companion object {
        fun newInstance() = PhotosFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_photos, container, false)
        arguments?.let {
            albumId = it.getString(ALBUM_ID)
        }

        viewModel = PhotosViewModel(albumId!!)
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler.layoutManager = GridLayoutManager(activity,3)

        viewModel.PhotoToShowDetail.observe(this, Observer {
            if(it!=null){
                val bundle = Bundle()
                bundle.putParcelable(PHOTO,it)
                ((activity)as MainActivity).navigateToPhotoDetail(bundle)
                viewModel.PhotoToShowDetail.value = null
            }
        })
        viewModel.errorMessage.observe(this, Observer {
            errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        setToolbar()
        swipeRefresh.setOnRefreshListener{viewModel.loadPosts()}
    }

    private fun setToolbar() {
        photo_toolbar.navigationIcon = ContextCompat.getDrawable(activity!!.applicationContext, R.drawable.ic_arrow_back)
        photo_toolbar.setNavigationOnClickListener {
            activity!!.onBackPressed()
        }
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }
}
