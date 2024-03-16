package id.aej.myflix.home.impl.presentation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import id.aej.myflix.design_system.utils.composable
import id.aej.myflix.design_system.utils.nonAnimationComposable
import id.aej.myflix.home.api.HomeFeature
import id.aej.myflix.home.impl.presentation.screen.home.HomeScreen
import id.aej.myflix.home.impl.presentation.screen.home.HomeViewModel
import id.aej.myflix.home.impl.presentation.screen.movie_detail.MovieDetailScreen
import id.aej.myflix.home.impl.presentation.screen.movie_detail.MovieDetailViewModel

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
      val viewModel = hiltViewModel<HomeViewModel>()
      HomeScreen(
        viewModel = viewModel,
        onMovieClick = { movieId ->
          navController.navigate(HomeRoute.MovieDetail.route.replace("{movieId}", movieId))
        }
      )
    }

    navGraphBuilder.composable(
      route = HomeRoute.MovieDetail.route,
      args = listOf(navArgument("movieId") { type = NavType.StringType })
    ) { navBackStackEntry ->
      val viewModel: MovieDetailViewModel = hiltViewModel()
      val movieId = navBackStackEntry.arguments?.getString("movieId").orEmpty()
      MovieDetailScreen(
        viewModel = viewModel,
        movieId = movieId,
        onBackClick = {
          navController.popBackStack()
        }
      )
    }
  }
}