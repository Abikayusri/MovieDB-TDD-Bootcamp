package abika.sinau.mymoviedb.domain.usecase

import abika.sinau.mymoviedb.domain.model.dto.DetailMovieDomain
import abika.sinau.mymoviedb.domain.repository.MovieRepository
import abika.sinau.mymoviedb.utils.ResultState
import abika.sinau.mymoviedb.utils.base.BaseSuspendUseCase
import javax.inject.Inject

class FetchDetailMovieUsecase @Inject constructor(
    private val repository: MovieRepository,
) : BaseSuspendUseCase<FetchDetailMovieUsecase.RequestValues, FetchDetailMovieUsecase.ResponseValues>() {

    class RequestValues(
        val movieId: Int
    ) : BaseSuspendUseCase.RequestValues

    class ResponseValues(
        val result: ResultState<DetailMovieDomain?>
    ) : BaseSuspendUseCase.ResponseValues

    override suspend fun execute(request: RequestValues): ResponseValues {

        return try {
            repository.fetchDetailMovie(request.movieId).let {
                ResponseValues(ResultState.Success(it))
            }
        } catch (e: Exception) {
            ResponseValues(ResultState.Error(e))
        }
    }
}