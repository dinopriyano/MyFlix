package id.aej.myflix.favorite.impl.presentation.screen.favorite

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Created by dinopriyano on 18/12/23.
 */

@Composable fun FavoriteScreen() {
  Text(
    modifier = Modifier.fillMaxSize(),
    text = "Favorite",
    color = MaterialTheme.colorScheme.onBackground
  )
}