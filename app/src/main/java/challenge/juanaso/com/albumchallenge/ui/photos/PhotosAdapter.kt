package challenge.juanaso.com.albumchallenge.ui.photos

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import challenge.juanaso.com.albumchallenge.viewmodel.AlbumItemViewModel
import challenge.juanaso.com.albumchallenge.R
import challenge.juanaso.com.albumchallenge.databinding.PhotoItemBinding
import challenge.juanaso.com.albumchallenge.model.Album
import challenge.juanaso.com.albumchallenge.model.Photo
import challenge.juanaso.com.albumchallenge.viewmodel.PhotoItemViewModel


class PhotosAdapter (val clickListener: (Photo) -> Unit): RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {
    private lateinit var photos:List<Photo>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosAdapter.ViewHolder {
        val binding: PhotoItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.photo_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotosAdapter.ViewHolder, position: Int) {
        holder.bind(photos[position],clickListener)
    }

    override fun getItemCount(): Int {
        return if(::photos.isInitialized) photos.size else 0
    }

    fun updatePhotos(photos:List<Photo>){
        this.photos = photos
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: PhotoItemBinding): RecyclerView.ViewHolder(binding.root){
        private val viewModel = PhotoItemViewModel()

        fun bind(photo: Photo, clickListener: (Photo) -> Unit){
            viewModel.bind(photo)
            binding.viewModel = viewModel
            binding.viewModel
            binding.root.setOnClickListener { clickListener(photo)}
        }
    }
}