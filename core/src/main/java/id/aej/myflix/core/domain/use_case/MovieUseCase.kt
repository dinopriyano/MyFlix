package id.aej.myflix.core.domain.use_case

import id.aej.myflix.core.data.source.remote.dto.response.WebResponse
import id.aej.myflix.core.domain.model.MovieItem
import id.aej.myflix.core.domain.model.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by dinopriyano on 15/02/24.
 */
interface MovieUseCase {

  suspend fun getMovies(genre: String): Flow<Resource<WebResponse<List<MovieItem>>>>

  suspend fun getMovieDetail(movieId: String):Flow<Resource<WebResponse<MovieItem>>>

  suspend fun storeWatchList(movieId: String): Flow<Resource<WebResponse<MovieItem>>>

  suspend fun getWatchList(): Flow<Resource<WebResponse<List<MovieItem>>>>

}