package id.aej.myflix.auth.impl

/**
 * Created by dinopriyano on 27/11/23.
 */
sealed class AuthRoute(val route: String) {

  object Login: AuthRoute("login")

  object Register: AuthRoute("register")

}