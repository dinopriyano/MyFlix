package id.aej.myflix.profile.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.aej.myflix.profile.api.ProfileFeature
import id.aej.myflix.profile.impl.presentation.ProfileFeatureImpl

/**
 * Created by dinopriyano on 27/11/23.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileModule {

  @Binds
  abstract fun bindProfileFeature(
    profileFeatureImpl: ProfileFeatureImpl
  ): ProfileFeature

}