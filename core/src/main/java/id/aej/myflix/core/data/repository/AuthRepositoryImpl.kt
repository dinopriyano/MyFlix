package id.aej.myflix.core.data.repository

import id.aej.myflix.core.data.source.remote.dto.request.LoginRequest
import id.aej.myflix.core.data.source.remote.dto.request.RegisterRequest
import id.aej.myflix.core.data.source.remote.dto.response.AuthResponse
import id.aej.myflix.core.data.source.remote.dto.response.WebResponse
import id.aej.myflix.core.data.source.remote.service.AuthService
import id.aej.myflix.core.domain.repository.AuthRepository

/**
 * Created by dinopriyano on 11/01/24.
 */
class AuthRepositoryImpl constructor(
  private val apiService: AuthService
): AuthRepository {
  override suspend fun login(request: LoginRequest): WebResponse<AuthResponse> {
    return apiService.login(request)
  }

  override suspend fun register(request: RegisterRequest): WebResponse<AuthResponse> {
    return apiService.register(request)
  }
}