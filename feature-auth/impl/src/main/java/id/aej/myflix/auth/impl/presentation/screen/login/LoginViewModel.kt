package id.aej.myflix.auth.impl.presentation.screen.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.aej.myflix.core.data.source.local.AppDataStore
import id.aej.myflix.core.data.source.remote.dto.request.LoginRequest
import id.aej.myflix.core.data.source.remote.dto.response.WebResponse
import id.aej.myflix.core.domain.model.Auth
import id.aej.myflix.core.domain.model.Resource
import id.aej.myflix.core.domain.use_case.AuthUseCase
import id.aej.myflix.core.presentation.BaseViewModel
import id.aej.myflix.core.presentation.BasicUiState
import id.aej.myflix.core.utils.InputValidator
import id.aej.myflix.design_system.domain.model.InputWrapper
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by dinopriyano on 07/02/24.
 */

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val authUseCase: AuthUseCase,
  private val dataStore: AppDataStore
): BaseViewModel() {

  var emailInput = mutableStateOf(InputWrapper(""))
    private set

  var passwordInput = mutableStateOf(InputWrapper(""))
    private set

  private var _uiState = MutableSharedFlow<BasicUiState<WebResponse<Auth>>>()
  val uiState = _uiState.asSharedFlow()

  fun storeToken(value: String) {
    viewModelScope.launch {
      dataStore.storeData(AppDataStore.TOKEN, value)
    }
  }

  fun onEmailInput(email: String) {
    val error = InputValidator.getEmailErrorOrNull(email)
    emailInput.value = InputWrapper(email, error)
  }

  fun onPasswordInput(password: String) {
    val error = InputValidator.getPasswordErrorOrNull(password)
    passwordInput.value = InputWrapper(password, error)
  }

  fun login() {
    viewModelScope.launch {
      authUseCase.login(
        LoginRequest(
          email = emailInput.value.value,
          password = passwordInput.value.value
        )
      ).runFlow {
        when (it) {
          is Resource.Loading -> {
            _uiState.emit(
              BasicUiState.Loading
            )
          }
          is Resource.Success -> {
            _uiState.emit(
              BasicUiState.Success(it.data)
            )
          }
          is Resource.Error -> {
            _uiState.emit(
              BasicUiState.Error(it.errorCode, it.message)
            )
          }
          else -> Unit
        }
      }
    }
  }

}