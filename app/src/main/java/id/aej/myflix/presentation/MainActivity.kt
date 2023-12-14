package id.aej.myflix.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import id.aej.myflix.auth.api.AuthFeature
import id.aej.myflix.design_system.presentation.theme.MyFlixTheme
import id.aej.myflix.home.api.HomeFeature
import id.aej.myflix.presentation.navigation.AppBottomBar
import id.aej.myflix.presentation.navigation.AppNavigation
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @Inject
  lateinit var authFeature: AuthFeature

  @Inject
  lateinit var homeFeature: HomeFeature

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MyFlixTheme {
        MyFlixApp(
          authFeature,
          homeFeature
        )
      }
    }
  }
}

@Composable fun MyFlixApp(
  authFeature: AuthFeature,
  homeFeature: HomeFeature
) {
  val navController = rememberNavController()

  Box(
    Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
    contentAlignment = Alignment.BottomCenter,
  ) {
    AppNavigation(
      startDestination = authFeature.authRoute,
      navController = navController,
      authFeature = authFeature,
      homeFeature = homeFeature
    )

    AppBottomBar(
      modifier = Modifier.clip(RoundedCornerShape(percent = 50)).background(Color.White),
      navController = navController,
      homeFeature = homeFeature)
  }
}