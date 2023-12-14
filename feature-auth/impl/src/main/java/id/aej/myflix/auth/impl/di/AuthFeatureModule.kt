package id.aej.myflix.auth.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.aej.myflix.auth.impl.AuthFeatureImpl

/**
 * Created by dinopriyano on 27/11/23.
 */

@Module
@InstallIn(SingletonComponent::class)
object AuthFeatureModule {

  @Provides
  fun provideAuthFeature() = AuthFeatureImpl()
  
}