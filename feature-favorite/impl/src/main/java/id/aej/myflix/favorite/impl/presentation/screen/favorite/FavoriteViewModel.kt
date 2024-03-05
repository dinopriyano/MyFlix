package id.aej.myflix.favorite.impl.presentation.screen.favorite

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

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
): BaseViewModel() {

    private var _uiState = MutableSharedFlow<BasicUiState<WebResponse<List<MovieItem>>>>()
    val uiState = _uiState.asSharedFlow()

    fun getWatchList() {
        viewModelScope.launch {
            movieUseCase.getWatchList().runFlow {
                when (val result = it) {
                    is Resource.Loading -> {
                        _uiState.emit(BasicUiState.Loading)
                    }
                    is Resource.Error -> {
                        _uiState.emit(BasicUiState.Error(result.errorCode, result.message))
                    }
                    is Resource.Success -> {
                        _uiState.emit(BasicUiState.Success(result.data))
                    }
                    else -> Unit
                }
            }
        }
    }
}