package id.aej.myflix.core.data.interactors

import id.aej.myflix.core.data.source.remote.SafeApiCall
import id.aej.myflix.core.data.source.remote.dto.response.WebResponse
import id.aej.myflix.core.data.source.remote.dto.response.toDomain
import id.aej.myflix.core.domain.model.MovieItem
import id.aej.myflix.core.domain.model.Resource
import id.aej.myflix.core.domain.repository.MovieRepository
import id.aej.myflix.core.domain.use_case.MovieUseCase
import id.aej.myflix.core.utils.execute
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

/**
 * Created by dinopriyano on 15/02/24.
 */
class MovieInteractors constructor(
  private val movieRepository: MovieRepository,
  private val coroutineContext: CoroutineContext
): MovieUseCase, SafeApiCall {
  override suspend fun getMovies(genre: String): Flow<Resource<WebResponse<List<MovieItem>>>> {
    return execute(coroutineContext) {
      safeApiCall {
        movieRepository.getMovies(genre).run {
          WebResponse(
            data?.map { it.toDomain() },
            success,
            message,
            statusCode,
            error
          )
        }
      }
    }
  }

  override suspend fun getMovieDetail(movieId: String): Flow<Resource<WebResponse<MovieItem>>> {
    return execute(coroutineContext) {
      safeApiCall {
        movieRepository.getMovieDetail(movieId).run {
          WebResponse(
            data?.toDomain(),
            success,
            message,
            statusCode,
            error
          )
        }
      }
    }
  }

  override suspend fun storeWatchList(movieId: String): Flow<Resource<WebResponse<MovieItem>>> {
    return execute(coroutineContext) {
      safeApiCall {
        movieRepository.storeWatchList(movieId).run {
          WebResponse(
            data?.toDomain(),
            success,
            message,
            statusCode,
            error
          )
        }
      }
    }
  }

  override suspend fun getWatchList(): Flow<Resource<WebResponse<List<MovieItem>>>> {
    return execute(coroutineContext) {
      safeApiCall {
        movieRepository.getWatchList().run {
          WebResponse(
            data?.map { it.toDomain() },
            success,
            message,
            statusCode,
            error
          )
        }
      }
    }
  }
}