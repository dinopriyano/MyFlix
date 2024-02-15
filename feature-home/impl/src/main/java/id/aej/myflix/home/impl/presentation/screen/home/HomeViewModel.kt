package id.aej.myflix.home.impl.presentation.screen.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.aej.myflix.core.data.source.remote.dto.response.WebResponse
import id.aej.myflix.core.domain.model.MovieItem
import id.aej.myflix.core.domain.model.Resource
import id.aej.myflix.core.domain.use_case.MovieUseCase
import id.aej.myflix.core.presentation.BaseViewModel
import id.aej.myflix.core.presentation.BasicUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by dinopriyano on 15/02/24.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val movieUseCase: MovieUseCase
): BaseViewModel() {

  private var _uiState = MutableSharedFlow<BasicUiState<WebResponse<List<MovieItem>>>>()
  val uiState = _uiState.asSharedFlow()

  fun getMovies(genre: String) {
    viewModelScope.launch {
      movieUseCase.getMovies(genre).runFlow {
        when (it) {
          is Resource.Loading -> {
            _uiState.emit(BasicUiState.Loading)
          }
          is Resource.Success -> {
            _uiState.emit(BasicUiState.Success(it.data))
          }
          is Resource.Error -> {
            _uiState.emit(BasicUiState.Error(it.errorCode, it.message))
          }
          else -> Unit
        }
      }
    }
  }
}