package id.aej.myflix.core.data.source.remote

import android.util.Log
import id.aej.myflix.core.data.source.local.AppDataStore
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpRedirect
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.serialization.gson.gson
import kotlinx.coroutines.flow.firstOrNull

/**
 * Created by dinopriyano on 11/01/24.
 */
class HttpClientFactory (
  private val appDataStore: AppDataStore
) {

  fun create() = HttpClient(OkHttp) {
    expectSuccess = false
    install(Logging) {
      logger = Logger.SIMPLE
      level = LogLevel.ALL
    }
    install(HttpTimeout) {
      socketTimeoutMillis = 30000
      requestTimeoutMillis = 30000
      connectTimeoutMillis = 30000
    }
    install(Auth) {
      bearer {
        // use this for exclude token on specific url
//        sendWithoutRequest { request ->
//          request.url.encodedPath.startsWith("/login")
//              || request.url.encodedPath.startsWith("/register")
//        }
        loadTokens {
          appDataStore.token.firstOrNull()?.let {
            BearerTokens(it, "")
          }
        }
        refreshTokens {
          appDataStore.token.firstOrNull()?.let {
            BearerTokens(it, "")
          }
        }
        sendWithoutRequest { true }
      }
    }
    install(ContentNegotiation) {
      gson {
        setPrettyPrinting()
        setLenient()
      }
    }
    install(HttpRedirect) {
      checkHttpMethod = false
    }
    install(ResponseObserver) {
      onResponse { response ->
        Log.d("Http status: ", "${response.status.value}")
        Log.d("Http response: ", response.toString())
      }
    }
    defaultRequest {
      host = "myflix.androidenthusiast.com"
      headers {
        append(HttpHeaders.ContentType, "application/json")
      }
    }
  }
}