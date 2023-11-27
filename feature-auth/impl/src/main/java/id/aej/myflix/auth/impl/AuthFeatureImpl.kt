package id.aej.myflix.auth.impl

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import id.aej.myflix.auth.api.AuthFeature
import id.aej.myflix.auth.impl.presentation.screen.login.LoginScreen
import id.aej.myflix.auth.impl.presentation.screen.register.RegisterScreen
import id.aej.myflix.design_system.utils.composable

/**
 * Created by dinopriyano on 27/11/23.
 */
class AuthFeatureImpl: AuthFeature {
  override val authRoute: String
    get() = AuthRoute.Login.route

  override fun registerGraph(
    navGraphBuilder: NavGraphBuilder, navController: NavController, modifier: Modifier
  ) {

    navGraphBuilder.composable(
      route = AuthRoute.Login.route
    ) {
      LoginScreen(
        onToRegister = {
          navController.navigate(AuthRoute.Register.route)
        }
      )
    }

    navGraphBuilder.composable(
      route = AuthRoute.Register.route
    ) {
      RegisterScreen(
        onToLoginScreen = {
          navController.popBackStack()
        }
      )
    }

  }

}