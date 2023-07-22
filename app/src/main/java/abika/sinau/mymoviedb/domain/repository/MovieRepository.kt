package abika.sinau.mymoviedb.domain.repository

import abika.sinau.mymoviedb.domain.model.dto.DetailMovieDomain
import abika.sinau.mymoviedb.domain.model.dto.ListMovieDomain
import abika.sinau.mymoviedb.domain.model.request.MovieListQuery
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun fetchDetailMovie(movieId: Int): DetailMovieDomain?

    fun fetchAllMoviePaging(request: MovieListQuery): Flow<PagingData<ListMovieDomain>>

    fun fetchPopularMoviePaging(request: MovieListQuery): Flow<PagingData<ListMovieDomain>>
}