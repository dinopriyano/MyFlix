package id.aej.myflix.auth.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.aej.myflix.auth.api.AuthFeature
import id.aej.myflix.auth.impl.AuthFeatureImpl

/**
 * Created by dinopriyano on 27/11/23.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

  @Binds
  abstract fun bindAuthFeature(
    authFeature: AuthFeatureImpl
  ): AuthFeature

}