package challenge.juanaso.com.albumchallenge.ui.photos

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import challenge.juanaso.com.albumchallenge.databinding.FragmentPhotosBinding
import challenge.juanaso.com.albumchallenge.R
import challenge.juanaso.com.albumchallenge.utils.ALBUM_ID
import challenge.juanaso.com.albumchallenge.viewmodel.PhotosViewModel
import kotlinx.android.synthetic.main.fragment_photos.*

class PhotosFragment : Fragment() {
    private var albumId: String? = null
    private lateinit var binding: FragmentPhotosBinding

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
    }
}
