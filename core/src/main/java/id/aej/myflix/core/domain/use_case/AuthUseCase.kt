package id.aej.myflix.core.domain.use_case

import id.aej.myflix.core.data.source.remote.dto.request.LoginRequest
import id.aej.myflix.core.data.source.remote.dto.request.RegisterRequest
import id.aej.myflix.core.data.source.remote.dto.response.WebResponse
import id.aej.myflix.core.domain.model.Auth
import id.aej.myflix.core.domain.model.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by dinopriyano on 11/01/24.
 */
interface AuthUseCase {
  suspend fun login(request: LoginRequest): Flow<Resource<WebResponse<Auth>>>
  suspend fun register(request: RegisterRequest): Flow<Resource<WebResponse<Auth>>>
}