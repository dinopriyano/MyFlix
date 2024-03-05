package id.aej.myflix.core.data.repository

import id.aej.myflix.core.data.source.remote.dto.response.MovieItemResponse
import id.aej.myflix.core.data.source.remote.dto.response.WebResponse
import id.aej.myflix.core.data.source.remote.service.MovieService
import id.aej.myflix.core.domain.repository.MovieRepository

/**
 * Created by dinopriyano on 15/02/24.
 */
class MovieRepositoryImpl constructor(
  private val movieService: MovieService
): MovieRepository {
  override suspend fun getMovies(genre: String): WebResponse<List<MovieItemResponse>> {
    return movieService.getMovies(genre)
  }

  override suspend fun getMovieDetail(movieId: String): WebResponse<MovieItemResponse> {
    return movieService.getMovieDetail(movieId)
  }

  override suspend fun storeWatchList(movieId: String): WebResponse<MovieItemResponse> {
    return movieService.storeWatchList(movieId)
  }

  override suspend fun getWatchList(): WebResponse<List<MovieItemResponse>> {
    return movieService.getWatchList()
  }

}