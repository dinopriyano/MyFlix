package id.aej.myflix.favorite.impl.presentation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import id.aej.myflix.design_system.utils.composable
import id.aej.myflix.design_system.utils.nonAnimationComposable
import id.aej.myflix.favorite.api.FavoriteFeature
import id.aej.myflix.favorite.impl.presentation.screen.favorite.FavoriteScreen

/**
 * Created by dinopriyano on 07/12/23.
 */
class FavoriteFeatureImpl: FavoriteFeature {
  override val favoriteRoute: String
    get() = FavoriteRoute.Favorite.route

  override fun registerGraph(
    navGraphBuilder: NavGraphBuilder, navController: NavController, modifier: Modifier
  ) {
    navGraphBuilder.nonAnimationComposable(
      route = FavoriteRoute.Favorite.route
    ) {
      FavoriteScreen()
    }
  }
}