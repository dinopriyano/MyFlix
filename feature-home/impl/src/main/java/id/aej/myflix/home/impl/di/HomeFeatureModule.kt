package id.aej.myflix.home.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.aej.myflix.home.impl.presentation.HomeFeatureImpl

/**
 * Created by dinopriyano on 27/11/23.
 */

@Module
@InstallIn(SingletonComponent::class)
object HomeFeatureModule {

  @Provides
  fun provideHomeFeature() = HomeFeatureImpl()
  
}