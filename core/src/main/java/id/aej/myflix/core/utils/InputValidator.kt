package id.aej.myflix.core.utils

/**
 * Created by dinopriyano on 07/02/24.
 */
object InputValidator {

  fun getEmailErrorOrNull(input: String): String? {
    return when {
      !input.isValidEmail() -> "Invalid email format"
      else -> null
    }
  }

  fun getPasswordErrorOrNull(input: String): String? {
    return when {
      !input.isValidPassword() -> "Password should have minimum eight characters, at least one letter, one number and one special character"
      else -> null
    }
  }

}