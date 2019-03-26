package challenge.juanaso.com.albumchallenge.ui.main

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import challenge.juanaso.com.albumchallenge.databinding.AlbumItemBinding
import challenge.juanaso.com.albumchallenge.viewmodel.AlbumItemViewModel
import challenge.juanaso.com.albumchallenge.R
import challenge.juanaso.com.albumchallenge.model.Album
import java.util.ArrayList


class AlbumAdapter (val clickListener: (Album) -> Unit): RecyclerView.Adapter<AlbumAdapter.ViewHolder>(), Filterable {
    private lateinit var albums:List<Album>
    private lateinit var albumsFull:List<Album>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumAdapter.ViewHolder {
        val binding: AlbumItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.album_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumAdapter.ViewHolder, position: Int) {
        holder.bind(albums[position],clickListener)
}

    override fun getItemCount(): Int {
        return if(::albums.isInitialized) albums.size else 0
    }

    fun updateAlbums(albums:List<Album>){
        this.albums = albums
        this.albumsFull = albums
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: AlbumItemBinding): RecyclerView.ViewHolder(binding.root){
        private val viewModel = AlbumItemViewModel()

        fun bind(album: Album, clickListener: (Album) -> Unit){
            viewModel.bind(album)
            binding.viewModel = viewModel
            binding.viewModel
            binding.root.setOnClickListener { clickListener(album)}
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val filteredList = ArrayList<Album>()
                if (charSequence == null || charSequence.isEmpty()){
                    filteredList.addAll(albumsFull)
                }else{
                    val filterPattern = charSequence.toString().trim { it <= ' ' }
                    for(item in albumsFull){
                        if (item.title!!.toLowerCase().contains(filterPattern)) {
                            filteredList.add(item)
                        }
                    }
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, filterResults: FilterResults?) {
                albums = filterResults!!.values as  MutableList<Album>
                notifyDataSetChanged()
            }
        }
    }
}