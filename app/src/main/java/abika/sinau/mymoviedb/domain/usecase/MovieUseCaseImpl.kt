package abika.sinau.mymoviedb.domain.usecase

import abika.sinau.mymoviedb.domain.repository.MovieRepository
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : MovieUseCase {
}