package abika.sinau.mymoviedb.domain.usecase

import abika.sinau.mymoviedb.domain.model.dto.ListMovieDomain
import abika.sinau.mymoviedb.domain.model.request.MovieListQuery
import abika.sinau.mymoviedb.domain.repository.MovieRepository
import abika.sinau.mymoviedb.utils.base.BaseUseCase
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPopularMovieUsecase @Inject constructor(
    private val repository: MovieRepository,
) : BaseUseCase<FetchPopularMovieUsecase.RequestValues, FetchPopularMovieUsecase.ResponseValues>() {

    class RequestValues(
        val query: MovieListQuery
    ) : BaseUseCase.RequestValues

    class ResponseValues(
        val result: Flow<PagingData<ListMovieDomain>>
    ) : BaseUseCase.ResponseValues

    override fun execute(request: RequestValues): ResponseValues {
        return ResponseValues(
            repository.fetchPopularMoviePaging(request.query)
        )
    }

}