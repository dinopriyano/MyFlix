package id.aej.myflix.core.data.interactors

import id.aej.myflix.core.data.source.remote.SafeApiCall
import id.aej.myflix.core.data.source.remote.dto.request.LoginRequest
import id.aej.myflix.core.data.source.remote.dto.request.RegisterRequest
import id.aej.myflix.core.data.source.remote.dto.response.AuthResponse
import id.aej.myflix.core.data.source.remote.dto.response.WebResponse
import id.aej.myflix.core.domain.model.Resource
import id.aej.myflix.core.domain.repository.AuthRepository
import id.aej.myflix.core.domain.use_case.AuthUseCase
import id.aej.myflix.core.utils.execute
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

/**
 * Created by dinopriyano on 11/01/24.
 */
class AuthInteractors constructor(
  private val coroutineContext: CoroutineContext,
  private val repository: AuthRepository
): AuthUseCase, SafeApiCall {
  override suspend fun login(request: LoginRequest): Flow<Resource<WebResponse<AuthResponse>>> {
    return execute(coroutineContext) {
      safeApiCall { repository.login(request) }
    }
  }

  override suspend fun register(request: RegisterRequest): Flow<Resource<WebResponse<AuthResponse>>> {
    return execute(coroutineContext) {
      safeApiCall { repository.register(request) }
    }
  }
}