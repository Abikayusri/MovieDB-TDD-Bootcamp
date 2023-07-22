package abika.sinau.mymoviedb.data.paging_source

import abika.sinau.mymoviedb.data.api.ApiService
import abika.sinau.mymoviedb.domain.model.dto.ListMovieDomain
import abika.sinau.mymoviedb.domain.model.request.MovieListQuery
import androidx.paging.PagingSource
import androidx.paging.PagingState

class ListMoviePagingSource(
    private val query: MovieListQuery,
    private val api: ApiService
) : PagingSource<Int, ListMovieDomain>() {

    override fun getRefreshKey(state: PagingState<Int, ListMovieDomain>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListMovieDomain> {
        query.page = params.key.takeIf { it != 0 } ?: 1
        val currentpage = query.page

        return try {
            val response = api.getAllMovie(query.toMap())
            val result = response.body()?.results
            val totalPages = response.body()?.totalPages ?: 1
            LoadResult.Page(
                data = result?.map { it.toDomain() } ?: listOf(),
                prevKey = null,
                nextKey = if (currentpage < totalPages) currentpage.plus(1) else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}