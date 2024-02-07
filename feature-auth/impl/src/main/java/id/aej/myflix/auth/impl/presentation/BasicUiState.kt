package id.aej.myflix.auth.impl.presentation

/**
 * Created by dinopriyano on 07/02/24.
 */
sealed class BasicUiState<out T> {
  data class Success<T>(val data: T): BasicUiState<T>()
  data class Error(val errorCode: Int, val message: String): BasicUiState<Nothing>()
  object Loading: BasicUiState<Nothing>()
  object Idle: BasicUiState<Nothing>()
}
