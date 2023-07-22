package abika.sinau.mymoviedb.di

import abika.sinau.mymoviedb.data.api.ApiService
import abika.sinau.mymoviedb.data.repository.MovieRepositoryImpl
import abika.sinau.mymoviedb.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): MovieRepository {
        return MovieRepositoryImpl(apiService)
    }
}