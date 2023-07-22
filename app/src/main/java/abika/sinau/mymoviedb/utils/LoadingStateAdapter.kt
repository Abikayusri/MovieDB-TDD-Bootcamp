package abika.sinau.mymoviedb.utils

import abika.sinau.mymoviedb.databinding.ItemLoadingStateBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView

class LoadingStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<LoadingStateAdapter.LoadingStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder {
        val binding =
            ItemLoadingStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadingStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadingStateViewHolder(private val binding: ItemLoadingStateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.apply {
                pbLoading.isVisible =
                    loadState is LoadState.Loading || loadState.endOfPaginationReached

                tvErrorState.isVisible =
                    loadState is LoadState.Error && loadState !is LoadState.Loading
                btnRetry.isVisible = loadState is LoadState.Error && loadState !is LoadState.Loading
                btnRetry.setOnClickListener { retry.invoke() }
            }
        }
    }
}

