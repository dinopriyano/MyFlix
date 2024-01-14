package id.aej.myflix.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.aej.myflix.core.data.source.local.AppDataStore
import id.aej.myflix.core.data.source.remote.HttpClientFactory
import io.ktor.client.HttpClient

/**
 * Created by dinopriyano on 11/01/24.
 */

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  val hashMappp = hashMapOf<String, Any>(
    "pageNumber" to 0,
    "keyword" to "Sepatu"
  )

  @Provides
  fun provideHttpClient(appDataStore: AppDataStore): HttpClient = HttpClientFactory(appDataStore).create()

}