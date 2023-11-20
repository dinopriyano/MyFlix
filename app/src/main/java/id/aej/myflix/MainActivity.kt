package id.aej.myflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import id.aej.myflix.design_system.presentation.theme.MyFlixTheme
import id.aej.myflix.home.impl.presentation.screen.home.HomeScreen

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MyFlixTheme {
        MyFlixApp()
      }
    }
  }
}

@Composable fun MyFlixApp() {
  Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
    HomeScreen()
  }
}