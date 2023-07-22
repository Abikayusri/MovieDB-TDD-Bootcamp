package abika.sinau.mymoviedb.data.api

import abika.sinau.mymoviedb.domain.model.response.ListMovieResponse
import abika.sinau.mymoviedb.domain.model.response.PagingBaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("discover/movie")
    suspend fun getAllMovie(
        @QueryMap query: Map<String, @JvmSuppressWildcards Any>
    ): Response<PagingBaseResponse<ListMovieResponse>>

}