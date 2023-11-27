package id.aej.myflix.design_system.utils

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import id.aej.myflix.design_system.presentation.navigation.FeatureEntry

/**
 * Created by dinopriyano on 27/11/23.
 */

fun NavGraphBuilder.register(
  featureEntry: FeatureEntry,
  navController: NavHostController,
  modifier: Modifier = Modifier
) {
  featureEntry.registerGraph(
    navGraphBuilder = this,
    navController = navController,
    modifier = modifier
  )
}

fun NavGraphBuilder.composable(
  route: String,
  args: List<NamedNavArgument> = emptyList(),
  deepLinks: List<NavDeepLink> = emptyList(),
  screen: @Composable (NavBackStackEntry) -> Unit
) {
  composable(
    route = route,
    arguments = args,
    deepLinks = deepLinks,
    enterTransition = {
      slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Left,
        tween(300)
      )
    },
    exitTransition = {
      slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Left,
        tween(300)
      )
    },
    popEnterTransition = {
      slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Right,
        tween(300)
      )
    },
    popExitTransition = {
      slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Right,
        tween(300)
      )
    }
  ) { backStackEntry ->
    screen.invoke(backStackEntry)
  }
}