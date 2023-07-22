package abika.sinau.mymoviedb.data.repository

import abika.sinau.mymoviedb.data.api.ApiService
import abika.sinau.mymoviedb.data.paging_source.ListMoviePagingSource
import abika.sinau.mymoviedb.data.paging_source.PopularMoviePagingSource
import abika.sinau.mymoviedb.domain.model.dto.DetailMovieDomain
import abika.sinau.mymoviedb.domain.model.dto.ListMovieDomain
import abika.sinau.mymoviedb.domain.model.request.MovieListQuery
import abika.sinau.mymoviedb.domain.repository.MovieRepository
import abika.sinau.mymoviedb.utils.api_handler.ApiHandler
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MovieRepository {
    override suspend fun fetchDetailMovie(movieId: Int): DetailMovieDomain? {
        val result = ApiHandler.handleApi {
            apiService.getDetailMovie(movieId)
        }?.toDomain()

        return result
    }

    override fun fetchAllMoviePaging(request: MovieListQuery): Flow<PagingData<ListMovieDomain>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ListMoviePagingSource(request, apiService)
            }
        ).flow
    }

    override fun fetchPopularMoviePaging(request: MovieListQuery): Flow<PagingData<ListMovieDomain>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PopularMoviePagingSource(request, apiService)
            }
        ).flow
    }
}