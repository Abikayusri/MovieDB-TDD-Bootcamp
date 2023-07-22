package abika.sinau.mymoviedb.di

import abika.sinau.mymoviedb.domain.repository.MovieRepository
import abika.sinau.mymoviedb.domain.usecase.MovieUseCase
import abika.sinau.mymoviedb.domain.usecase.MovieUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideUseCase(repository: MovieRepository): MovieUseCase {
        return MovieUseCaseImpl(repository)
    }
}