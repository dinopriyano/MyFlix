package id.aej.myflix.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.aej.myflix.auth.impl.AuthRoute
import id.aej.myflix.core.data.source.local.AppDataStore
import id.aej.myflix.core.presentation.BaseViewModel
import id.aej.myflix.home.impl.presentation.HomeRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by dinopriyano on 23/02/24.
 */

@HiltViewModel
class MainViewModel @Inject constructor(
  private val dataStore: AppDataStore
): BaseViewModel() {

  private var _isLoading = MutableStateFlow(true)
  val isLoading = _isLoading.asStateFlow()

  private var _startDestination = MutableStateFlow("")
  val startDestination = _startDestination.asStateFlow()

  init {
    getToken()
  }

  private fun getToken() {
    viewModelScope.launch {
      _startDestination.value = if (dataStore.token.first().isNotEmpty()) {
        HomeRoute.Home.route
      } else {
        AuthRoute.Login.route
      }
      _isLoading.value = false
    }
  }

}