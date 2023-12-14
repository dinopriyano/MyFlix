package id.aej.myflix.home.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.aej.myflix.home.api.HomeFeature
import id.aej.myflix.home.impl.presentation.HomeFeatureImpl

/**
 * Created by dinopriyano on 27/11/23.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeModule {

  @Binds
  abstract fun bindHomeFeature(
    homeFeature: HomeFeatureImpl
  ): HomeFeature

}