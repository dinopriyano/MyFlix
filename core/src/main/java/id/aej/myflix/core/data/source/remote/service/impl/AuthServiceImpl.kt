package id.aej.myflix.core.data.source.remote.service.impl

import id.aej.myflix.core.data.source.remote.HttpRoutes
import id.aej.myflix.core.data.source.remote.dto.request.LoginRequest
import id.aej.myflix.core.data.source.remote.dto.request.RegisterRequest
import id.aej.myflix.core.data.source.remote.dto.response.AuthResponse
import id.aej.myflix.core.data.source.remote.dto.response.WebResponse
import id.aej.myflix.core.data.source.remote.service.AuthService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url

/**
 * Created by dinopriyano on 11/01/24.
 */
class AuthServiceImpl constructor(
  private val httpClient: HttpClient
): AuthService {
  override suspend fun login(request: LoginRequest): WebResponse<AuthResponse> {
    return httpClient.post {
      url(HttpRoutes.LOGIN_URL)
      setBody(request)
    }.body()
  }

  override suspend fun register(request: RegisterRequest): WebResponse<AuthResponse> {
    return httpClient.post {
      url(HttpRoutes.REGISTER_URL)
      setBody(request)
    }.body()
  }
}