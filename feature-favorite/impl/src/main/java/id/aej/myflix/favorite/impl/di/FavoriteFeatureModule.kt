package id.aej.myflix.favorite.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.aej.myflix.favorite.impl.presentation.FavoriteFeatureImpl

/**
 * Created by dinopriyano on 27/11/23.
 */

@Module
@InstallIn(SingletonComponent::class)
object FavoriteFeatureModule {

  @Provides
  fun provideFavoriteFeature() = FavoriteFeatureImpl()
  
}