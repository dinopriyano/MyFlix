package id.aej.myflix.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.aej.myflix.core.data.source.local.AppDataStore
import javax.inject.Singleton

/**
 * Created by dinopriyano on 11/01/24.
 */

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

  @Provides
  @Singleton
  fun provideAppDataStore(@ApplicationContext context: Context): AppDataStore {
    return AppDataStore(context)
  }

}