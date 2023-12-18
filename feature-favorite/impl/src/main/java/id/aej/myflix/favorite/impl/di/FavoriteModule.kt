package id.aej.myflix.favorite.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.aej.myflix.favorite.api.FavoriteFeature
import id.aej.myflix.favorite.impl.presentation.FavoriteFeatureImpl

/**
 * Created by dinopriyano on 27/11/23.
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoriteModule {

  @Binds
  abstract fun bindFavoriteFeature(
    favoriteFeature: FavoriteFeatureImpl
  ): FavoriteFeature

}