package challenge.juanaso.com.albumchallenge.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import challenge.juanaso.com.albumchallenge.MainActivity
import challenge.juanaso.com.albumchallenge.R
import challenge.juanaso.com.albumchallenge.databinding.MainFragmentBinding
import challenge.juanaso.com.albumchallenge.di.ViewModelFactory
import challenge.juanaso.com.albumchallenge.utils.ALBUM_ID
import challenge.juanaso.com.albumchallenge.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    private var errorSnackbar: Snackbar? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSwipeToRefresh()
        viewModel.loadPosts()
    }

    private fun setSwipeToRefresh() {
        main_swipeRefresh.setOnRefreshListener{viewModel.loadPosts()}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        this.setHasOptionsMenu(true)
        viewModel =   ViewModelProviders.of(this, ViewModelFactory(this.activity!!)).get(MainViewModel::class.java)
        binding.userRecycler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        viewModel.errorMessage.observe(this, Observer {
            errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel

        viewModel.albumToShowContent.observe(this, Observer {
            if(it!=null){
                val bundle = Bundle()
                bundle.putString(ALBUM_ID, it.id!!)
                ((activity)as MainActivity).navigateToPhotos(bundle)
                viewModel.albumToShowContent.value = null;
            }
        })
        return binding.root
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }
}
