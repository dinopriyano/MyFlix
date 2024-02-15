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
}