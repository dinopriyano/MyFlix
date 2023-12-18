package id.aej.myflix.profile.impl.presentation.screen.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Created by dinopriyano on 18/12/23.
 */

@Composable fun ProfileScreen() {
  Text(
    modifier = Modifier.fillMaxSize(),
    text = "Profile",
    color = MaterialTheme.colorScheme.onBackground
  )
}