package id.aej.myflix.core.data.source.remote.service

import id.aej.myflix.core.data.source.remote.dto.request.LoginRequest
import id.aej.myflix.core.data.source.remote.dto.request.RegisterRequest
import id.aej.myflix.core.data.source.remote.dto.response.AuthResponse
import id.aej.myflix.core.data.source.remote.dto.response.WebResponse

/**
 * Created by dinopriyano on 11/01/24.
 */
interface AuthService {
  suspend fun login(request: LoginRequest): WebResponse<AuthResponse>
  suspend fun register(request: RegisterRequest): WebResponse<AuthResponse>
}