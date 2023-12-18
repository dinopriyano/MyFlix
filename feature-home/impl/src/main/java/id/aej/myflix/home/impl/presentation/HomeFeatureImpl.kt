package id.aej.myflix.home.impl.presentation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import id.aej.myflix.design_system.utils.composable
import id.aej.myflix.design_system.utils.nonAnimationComposable
import id.aej.myflix.home.api.HomeFeature
import id.aej.myflix.home.impl.presentation.screen.home.HomeScreen

/**
 * Created by dinopriyano on 07/12/23.
 */
class HomeFeatureImpl: HomeFeature {
  override val homeRoute: String
    get() = HomeRoute.Home.route

  override fun registerGraph(
    navGraphBuilder: NavGraphBuilder, navController: NavController, modifier: Modifier
  ) {
    navGraphBuilder.nonAnimationComposable(
      route = HomeRoute.Home.route
    ) {
      HomeScreen()
    }
  }
}