package abika.sinau.mymoviedb.presentation.main

import abika.sinau.mymoviedb.databinding.ActivityMainBinding
import abika.sinau.mymoviedb.domain.model.dto.ListMovieDomain
import abika.sinau.mymoviedb.presentation.main.adapter.AllMovieAdapter
import abika.sinau.mymoviedb.utils.LoadingStateAdapter
import abika.sinau.mymoviedb.utils.base.BaseActivity
import abika.sinau.mymoviedb.utils.gone
import abika.sinau.mymoviedb.utils.toastShort
import abika.sinau.mymoviedb.utils.visible
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
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

    //    private val popularMovieAdapter = PopularMovieAdapter()
    private val allMovieAdapter = AllMovieAdapter()

    override fun setupViews() {
        with(binding) {
            swipeRefresh.setOnRefreshListener {
//                    fetchPopularMovie()
                fetchAllMovie()
            }

//            fetchPopularMovie()
            fetchAllMovie()

//            setupPopularMovie()
            setupAllMovie()
        }
    }

    private fun fetchPopularMovie() {
        lifecycleScope.launch {
            launch {
                viewModel.fetchPopularMovie().collectLatest {
//                    popularMovieAdapter.submitData(it)
                }
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

    private fun setupPopularMovie() {
        with(binding) {
//            rvPopularMovie.adapter = popularMovieAdapter

//            popularMovieAdapter.setOnItemClickListener(object :
//                PopularMovieAdapter.OnClickListener {
//                override fun onClickItem(item: ListMovieDomain) {
////                    MovieDetailActivity.navigate(this@MainActivity, item.id)
//                    toastShort("Menekan: ${item.title}")
//                }
//            })

//            popularMovieAdapter.addLoadStateListener { loadStates: CombinedLoadStates ->
//                rvPopularMovie.isVisible = loadStates.source.refresh is LoadState.NotLoading
//
//                val errorState = loadStates.source.append as? LoadState.Error
//                    ?: loadStates.source.prepend as? LoadState.Error
//                    ?: loadStates.append as? LoadState.Error
//                    ?: loadStates.prepend as? LoadState.Error
//                    ?: loadStates.source.refresh as? LoadState.Error
//                errorState?.let {
////                handleErrorState(it.error)
//                }
//            }
        }
    }

    private fun setupAllMovie() {
        with(binding) {
            rvAllMovie.adapter = allMovieAdapter
            rvAllMovie.adapter = allMovieAdapter.withLoadStateFooter(
                LoadingStateAdapter()
            )

            allMovieAdapter.setOnItemClickListener(object : AllMovieAdapter.OnClickListener {
                override fun onClickItem(item: ListMovieDomain) {
//                    MovieDetailActivity.navigate(this@MainActivity, item.id)
                    toastShort("Menekan: ${item.title}")
                }
            })

            allMovieAdapter.addLoadStateListener { loadStates: CombinedLoadStates ->

                val isLoading = loadStates.source.refresh is LoadState.Loading
                if (isLoading) inclShimmer.root.visible() else inclShimmer.root.gone()

                val isDataEmpty =
                    loadStates.source.refresh is LoadState.NotLoading && allMovieAdapter.itemCount == 0

                rvAllMovie.isVisible = loadStates.source.refresh is LoadState.NotLoading
//                rvAllMovie.isVisible = !isDataEmpty

                val errorState = loadStates.source.append as? LoadState.Error
                    ?: loadStates.source.prepend as? LoadState.Error
                    ?: loadStates.append as? LoadState.Error
                    ?: loadStates.prepend as? LoadState.Error
                    ?: loadStates.source.refresh as? LoadState.Error
                errorState?.let {
//                handleErrorState(it.error)
                }
            }
        }
    }

    override fun generalError(
        activity: Activity,
        lifecycle: Lifecycle,
        throwable: Throwable,
        anchor: View?
    ) {

    }
}
