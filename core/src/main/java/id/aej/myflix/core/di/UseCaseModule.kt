package id.aej.myflix.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import id.aej.myflix.core.data.interactors.AuthInteractors
import id.aej.myflix.core.data.interactors.MovieInteractors
import id.aej.myflix.core.domain.repository.AuthRepository
import id.aej.myflix.core.domain.repository.MovieRepository
import id.aej.myflix.core.domain.use_case.AuthUseCase
import id.aej.myflix.core.domain.use_case.MovieUseCase
import kotlinx.coroutines.Dispatchers

/**
 * Created by dinopriyano on 07/02/24.
 */

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

  @Provides
  fun authUseCase(authRepository: AuthRepository): AuthUseCase = AuthInteractors(Dispatchers.IO, authRepository)

  @Provides
  fun movieUseCase(movieRepository: MovieRepository): MovieUseCase = MovieInteractors(movieRepository, Dispatchers.IO)

}