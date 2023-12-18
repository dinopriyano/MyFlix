package id.aej.myflix.profile.impl.presentation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import id.aej.myflix.design_system.utils.nonAnimationComposable
import id.aej.myflix.profile.api.ProfileFeature
import id.aej.myflix.profile.impl.presentation.screen.profile.ProfileScreen

/**
 * Created by dinopriyano on 07/12/23.
 */
class ProfileFeatureImpl: ProfileFeature {
  override val profileRoute: String
    get() = ProfileRoute.Profile.route

  override fun registerGraph(
    navGraphBuilder: NavGraphBuilder, navController: NavController, modifier: Modifier
  ) {
    navGraphBuilder.nonAnimationComposable(
      route = ProfileRoute.Profile.route
    ) {
      ProfileScreen()
    }
  }
}