package id.aej.myflix.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.aej.myflix.core.data.source.local.AppDataStore
import id.aej.myflix.core.data.source.remote.HttpClientFactory
import id.aej.myflix.core.data.source.remote.service.AuthService
import id.aej.myflix.core.data.source.remote.service.MovieService
import id.aej.myflix.core.data.source.remote.service.impl.AuthServiceImpl
import id.aej.myflix.core.data.source.remote.service.impl.MovieServiceImpl
import io.ktor.client.HttpClient

/**
 * Created by dinopriyano on 11/01/24.
 */

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  fun provideHttpClient(appDataStore: AppDataStore): HttpClient = HttpClientFactory(appDataStore).create()

  @Provides
  fun provideAuthService(httpClient: HttpClient): AuthService = AuthServiceImpl(httpClient)

  @Provides
  fun provideMovieService(httpClient: HttpClient): MovieService = MovieServiceImpl(httpClient)

}