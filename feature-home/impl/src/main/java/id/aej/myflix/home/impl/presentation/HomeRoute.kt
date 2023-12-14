package id.aej.myflix.home.impl.presentation

/**
 * Created by dinopriyano on 07/12/23.
 */
sealed class HomeRoute(val route: String) {
  object Home: HomeRoute("home")

}