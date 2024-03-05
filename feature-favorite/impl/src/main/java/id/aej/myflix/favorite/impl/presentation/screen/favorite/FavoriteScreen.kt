package id.aej.myflix.favorite.impl.presentation.screen.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import id.aej.myflix.core.domain.model.MovieItem
import id.aej.myflix.core.presentation.BasicUiState
import id.aej.myflix.favorite.impl.R

/**
 * Created by dinopriyano on 18/12/23.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun FavoriteScreen(
  viewModel: FavoriteViewModel
) {

  val uiState by viewModel.uiState.collectAsState(BasicUiState.Idle)

  LaunchedEffect(Unit) {
    viewModel.getWatchList()
  }

  Scaffold (
    modifier = Modifier.fillMaxSize(),
    topBar = {
      CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
          containerColor = MaterialTheme.colorScheme.background,
          titleContentColor = MaterialTheme.colorScheme.onBackground
        ),
        title = {
          Text(
            text = stringResource(id = R.string.watch_list_txt),
            style = MaterialTheme.typography.titleSmall
          )
        }
      )
    }
  ) { paddingValues ->
    when (val state = uiState) {
      is BasicUiState.Success -> {
        LazyVerticalGrid(
          modifier = Modifier.fillMaxSize().padding(paddingValues),
          horizontalArrangement = Arrangement.spacedBy(16.dp),
          verticalArrangement = Arrangement.spacedBy(16.dp),
          contentPadding = PaddingValues(24.dp),
          columns = GridCells.Fixed(2)
        ) {
          items(state.data.data.orEmpty()) {
            ImageItem(
              modifier = Modifier
                .fillMaxWidth()
                .aspectRatio((2.0 / 3.0).toFloat()),
              movie = it
            )
          }
        }
      }
      else -> Unit
    }
  }
}

@Composable
fun ImageItem(
  modifier: Modifier,
  movie: MovieItem
) {
  AsyncImage(
    modifier = modifier.clip(RoundedCornerShape(16.dp)),
    contentScale = ContentScale.Crop,
    model = movie.posterUrl,
    contentDescription = null
  )
}