package id.aej.myflix.home.impl.presentation.screen.movie_detail

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
class MovieDetailViewModel @Inject constructor(
    private val useCase: MovieUseCase
): BaseViewModel() {

    private var _getMovieState = MutableSharedFlow<BasicUiState<WebResponse<MovieItem>>>()
    val getMovieUiStateState = _getMovieState.asSharedFlow()

    private var _storeWatchListState = MutableSharedFlow<BasicUiState<WebResponse<MovieItem>>>()
    val storeWatchListUiStateState = _storeWatchListState.asSharedFlow()

    fun getMovies(movieId: String) {
        viewModelScope.launch {
            useCase.getMovieDetail(movieId).runFlow {
                when (it) {
                    is Resource.Loading -> {
                        _getMovieState.emit(BasicUiState.Loading)
                    }
                    is Resource.Success -> {
                        _getMovieState.emit(BasicUiState.Success(it.data))
                    }
                    is Resource.Error -> {
                        _getMovieState.emit(BasicUiState.Error(it.errorCode, it.message))
                    }
                    else -> Unit
                }
            }
        }
    }

    fun storeWatchList(movieId: String) {
        viewModelScope.launch {
            useCase.storeWatchList(movieId).runFlow {
                when (it) {
                    is Resource.Loading -> {
                        _storeWatchListState.emit(BasicUiState.Loading)
                    }
                    is Resource.Success -> {
                        _storeWatchListState.emit(BasicUiState.Success(it.data))
                    }
                    is Resource.Error -> {
                        _storeWatchListState.emit(BasicUiState.Error(it.errorCode, it.message))
                    }
                    else -> Unit
                }
            }
        }
    }
}