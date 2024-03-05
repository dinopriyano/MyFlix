package id.aej.myflix.core.domain.repository

import id.aej.myflix.core.data.source.remote.dto.response.MovieItemResponse
import id.aej.myflix.core.data.source.remote.dto.response.WebResponse

/**
 * Created by dinopriyano on 15/02/24.
 */
interface MovieRepository {

  suspend fun getMovies(genre: String): WebResponse<List<MovieItemResponse>>

  suspend fun getMovieDetail(movieId: String): WebResponse<MovieItemResponse>

  suspend fun storeWatchList(movieId: String): WebResponse<MovieItemResponse>

  suspend fun getWatchList(): WebResponse<List<MovieItemResponse>>

}