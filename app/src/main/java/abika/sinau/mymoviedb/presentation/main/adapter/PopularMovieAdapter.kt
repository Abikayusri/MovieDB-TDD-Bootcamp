package abika.sinau.mymoviedb.presentation.main.adapter

import abika.sinau.mymoviedb.BuildConfig.IMAGE_PATH
import abika.sinau.mymoviedb.databinding.ItemMovieHorizontalBinding
import abika.sinau.mymoviedb.domain.model.dto.ListMovieDomain
import abika.sinau.mymoviedb.utils.loadImage
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class PopularMovieAdapter :
    PagingDataAdapter<ListMovieDomain, PopularMovieAdapter.PopularMovieViewHolder>(
        PopularMovieDiffUtil
    ) {

    private var callbacks: OnClickListener? = null

    object PopularMovieDiffUtil : DiffUtil.ItemCallback<ListMovieDomain>() {
        override fun areItemsTheSame(
            oldItem: ListMovieDomain,
            newItem: ListMovieDomain
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ListMovieDomain,
            newItem: ListMovieDomain
        ) = oldItem == newItem
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        val data = getItem(position)
        data?.let {
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieHorizontalBinding.inflate(inflater, parent, false)

        return PopularMovieViewHolder(binding)
    }

    override fun getItemCount(): Int = 10

    inner class PopularMovieViewHolder(
        private val binding: ItemMovieHorizontalBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListMovieDomain) {
            binding.apply {
                tvTitle.text = item.title
                ivPoster.loadImage(
                    IMAGE_PATH + item.posterPath,
//                    R.drawable.ic_broken_image_grey_24,
//                    R.drawable.ic_broken_image_grey_24
                )

                val rate = item.voteCount.toString()
                tvRate.text = rate

                root.setOnClickListener {
                    callbacks?.onClickItem(item)
                }
            }
        }
    }

    fun setOnItemClickListener(callbacks: OnClickListener) {
        this.callbacks = callbacks
    }

    interface OnClickListener {
        fun onClickItem(item: ListMovieDomain)
    }
}