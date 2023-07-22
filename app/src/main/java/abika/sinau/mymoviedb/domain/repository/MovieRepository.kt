package abika.sinau.mymoviedb.domain.repository

import abika.sinau.mymoviedb.domain.model.dto.ListMovieDomain
import abika.sinau.mymoviedb.domain.model.request.MovieListQuery
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun fetchAllMoviePaging(request: MovieListQuery): Flow<PagingData<ListMovieDomain>>
}