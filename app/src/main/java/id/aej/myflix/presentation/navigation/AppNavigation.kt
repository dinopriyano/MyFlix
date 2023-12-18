package id.aej.myflix.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import id.aej.myflix.auth.api.AuthFeature
import id.aej.myflix.design_system.utils.register
import id.aej.myflix.favorite.api.FavoriteFeature
import id.aej.myflix.home.api.HomeFeature
import id.aej.myflix.profile.api.ProfileFeature

/**
 * Created by dinopriyano on 27/11/23.
 */

@Composable fun AppNavigation(
  modifier: Modifier = Modifier,
  startDestination: String,
  navController: NavHostController,
  authFeature: AuthFeature,
  homeFeature: HomeFeature,
  favoriteFeature: FavoriteFeature,
  profileFeature: ProfileFeature
) {

  NavHost(
    navController = navController,
    startDestination = startDestination,
    modifier = modifier
  ) {

    register(
      authFeature,
      navController
    )

    register(
      homeFeature,
      navController
    )

    register(
      favoriteFeature,
      navController
    )

    register(
      profileFeature,
      navController
    )

  }

}