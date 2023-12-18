package id.aej.myflix.profile.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.aej.myflix.profile.impl.presentation.ProfileFeatureImpl

/**
 * Created by dinopriyano on 27/11/23.
 */

@Module
@InstallIn(SingletonComponent::class)
object ProfileFeatureModule {

  @Provides
  fun provideProfileFeature() = ProfileFeatureImpl()
  
}