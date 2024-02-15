package id.aej.myflix.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import id.aej.myflix.core.data.repository.AuthRepositoryImpl
import id.aej.myflix.core.data.repository.MovieRepositoryImpl
import id.aej.myflix.core.data.source.remote.service.AuthService
import id.aej.myflix.core.data.source.remote.service.MovieService
import id.aej.myflix.core.domain.repository.AuthRepository
import id.aej.myflix.core.domain.repository.MovieRepository

/**
 * Created by dinopriyano on 07/02/24.
 */

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

  @Provides
  fun provideAuthRepository(authService: AuthService): AuthRepository = AuthRepositoryImpl(authService)

  @Provides
  fun provideMovieRepository(movieService: MovieService): MovieRepository = MovieRepositoryImpl(movieService)

}