package id.aej.myflix.design_system.domain.model

/**
 * Created by dinopriyano on 27/11/23.
 */
data class InputWrapper<T>(
  val value: T,
  val error: String? = null
)
