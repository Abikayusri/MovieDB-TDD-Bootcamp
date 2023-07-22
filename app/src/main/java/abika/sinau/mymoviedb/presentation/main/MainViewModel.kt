package abika.sinau.mymoviedb.presentation.main

import abika.sinau.mymoviedb.domain.model.dto.ListMovieDomain
import abika.sinau.mymoviedb.domain.model.request.MovieListQuery
import abika.sinau.mymoviedb.domain.usecase.FetchAllMovieUsecase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val allMovieUsecase: FetchAllMovieUsecase
) : ViewModel() {

    private val movieQuery by lazy { MovieListQuery() }

    fun fetchAllMovie(): Flow<PagingData<ListMovieDomain>> {
        return allMovieUsecase.execute(
            FetchAllMovieUsecase.RequestValues(
                movieQuery
            )
        ).result.cachedIn(viewModelScope)
    }
}