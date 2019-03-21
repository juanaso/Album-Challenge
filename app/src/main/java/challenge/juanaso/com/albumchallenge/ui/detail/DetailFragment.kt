package challenge.juanaso.com.albumchallenge.ui.detail

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import challenge.juanaso.com.albumchallenge.R
import challenge.juanaso.com.albumchallenge.databinding.FragmentDetailBinding
import challenge.juanaso.com.albumchallenge.model.Photo
import challenge.juanaso.com.albumchallenge.utils.PHOTO
import challenge.juanaso.com.albumchallenge.utils.USER_ID
import challenge.juanaso.com.albumchallenge.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment(), FragmentManager.OnBackStackChangedListener,AppBarLayout.OnOffsetChangedListener {

    private var photo: Photo? = null

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBinding
    companion object {
        fun newInstance() = DetailFragment()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        arguments?.let {
            photo = it.getParcelable(PHOTO)
        }
        viewModel = DetailViewModel(photo!!)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSwipeToRefresh()
        setToolbar()
    }

    private fun setToolbar() {
        appBar.addOnOffsetChangedListener(this)
        activity_user_detail_toolbar.navigationIcon = ContextCompat.getDrawable(activity!!.applicationContext, R.drawable.ic_arrow_back)
        activity_user_detail_toolbar.setNavigationOnClickListener {
            activity!!.onBackPressed()
        }
    }

    private fun setSwipeToRefresh() {
        detail_swipeRefresh.setOnRefreshListener{viewModel.loadPhoto()}
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        detail_swipeRefresh.isEnabled = (verticalOffset == 0)
    }

    override fun onBackStackChanged() {
        activity!!.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        activity!!.onBackPressed()
        return true
    }

}
