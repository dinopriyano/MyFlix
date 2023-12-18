package id.aej.myflix.profile.impl.presentation

/**
 * Created by dinopriyano on 07/12/23.
 */
sealed class ProfileRoute(val route: String) {
  object Profile: ProfileRoute("profile")

}