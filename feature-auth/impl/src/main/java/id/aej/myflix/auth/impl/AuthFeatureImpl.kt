package id.aej.myflix.auth.impl

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import id.aej.myflix.auth.api.AuthFeature
import id.aej.myflix.auth.impl.presentation.screen.login.LoginScreen
import id.aej.myflix.auth.impl.presentation.screen.login.LoginViewModel
import id.aej.myflix.auth.impl.presentation.screen.register.RegisterScreen
import id.aej.myflix.design_system.utils.composable
import id.aej.myflix.home.impl.presentation.HomeRoute

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
      val viewModel = hiltViewModel<LoginViewModel>()
      LoginScreen(
        viewModel = viewModel,
        onToRegister = {
          navController.navigate(AuthRoute.Register.route)
        },
        onLoginSuccess = {
          navController.navigate(HomeRoute.Home.route)
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