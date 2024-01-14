package id.aej.myflix.core.domain.model

/**
 * Created by dinopriyano on 11/01/24.
 */
sealed class Resource<out T> {
  data class Success<T>(val data: T): Resource<T>()
  data class Error(val errorCode: Int, val message: String): Resource<Nothing>()
  object Loading: Resource<Nothing>()
  object Empty: Resource<Nothing>()
}
