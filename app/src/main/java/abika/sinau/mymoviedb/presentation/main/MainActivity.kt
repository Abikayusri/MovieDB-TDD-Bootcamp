package abika.sinau.mymoviedb.presentation.main

import abika.sinau.mymoviedb.R
import abika.sinau.mymoviedb.databinding.ActivityMainBinding
import abika.sinau.mymoviedb.presentation.main.adapter.AllMovieAdapter
import abika.sinau.mymoviedb.utils.LoadingStateAdapter
import abika.sinau.mymoviedb.utils.base.BaseActivity
import abika.sinau.mymoviedb.utils.gone
import abika.sinau.mymoviedb.utils.visible
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    private val allMovieAdapter = AllMovieAdapter()

    override fun setupViews() {
        with(binding) {

            setupAllMovie()
            fetchAllMovie()

            swipeRefresh.setOnRefreshListener {
                fetchAllMovie()
            }

        }
    }

    private fun fetchAllMovie() {
        lifecycleScope.launch {
            launch {
                viewModel.fetchAllMovie().collectLatest {
                    allMovieAdapter.submitData(it)
                }
            }
        }
    }

    private fun setupAllMovie() {
        with(binding) {
            rvAllMovie.adapter = allMovieAdapter
            rvAllMovie.adapter = allMovieAdapter.withLoadStateFooter(
                LoadingStateAdapter {
                    allMovieAdapter.retry()
                }
            )

            allMovieAdapter.addLoadStateListener { loadStates: CombinedLoadStates ->

                swipeRefresh.isRefreshing = false
                val isLoading = loadStates.source.refresh is LoadState.Loading
                allMovieAdapter.withLoadStateFooter(
                    LoadingStateAdapter {
                        allMovieAdapter.retry()
                    }
                )

                val isDataEmpty =
                    loadStates.source.refresh is LoadState.NotLoading && allMovieAdapter.itemCount == 0

                if (isLoading) inclShimmer.root.visible() else inclShimmer.root.gone()
                if (isDataEmpty) setupEmptyState(true) else cgEmptyState.gone()

                rvAllMovie.isVisible = !isDataEmpty

                val errorState = loadStates.source.append as? LoadState.Error
                    ?: loadStates.source.prepend as? LoadState.Error
                    ?: loadStates.append as? LoadState.Error
                    ?: loadStates.prepend as? LoadState.Error
                    ?: loadStates.source.refresh as? LoadState.Error
                errorState?.let {
                    if (allMovieAdapter.itemCount == 0) {
                        handleErrorApiState(it.error) {
                            setupEmptyState(false)
                        }
                    }
                }
            }
        }
    }

    private fun setupEmptyState(isEmpty: Boolean) {
        with(binding) {
            cgEmptyState.visible()
            rvAllMovie.gone()

            tvDataEmpty.text =
                if (isEmpty) getString(R.string.label_data_is_empty) else getString(R.string.label_no_internet_connection)

            btnRetry.setOnClickListener {
                fetchAllMovie()
            }
        }
    }
}
