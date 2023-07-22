package abika.sinau.mymoviedb.data.api

import abika.sinau.mymoviedb.domain.model.response.DetailMovieResponse
import abika.sinau.mymoviedb.domain.model.response.ListMovieResponse
import abika.sinau.mymoviedb.domain.model.response.PagingBaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ApiService {

    @GET("discover/movie")
    suspend fun getAllMovie(
        @QueryMap query: Map<String, @JvmSuppressWildcards Any>
    ): Response<PagingBaseResponse<ListMovieResponse>>

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @QueryMap query: Map<String, @JvmSuppressWildcards Any>
    ): Response<PagingBaseResponse<ListMovieResponse>>

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movieId: Int?
    ): Response<DetailMovieResponse>
}