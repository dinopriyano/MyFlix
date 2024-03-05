package id.aej.myflix.core.data.source.remote.dto.response

import id.aej.myflix.core.domain.model.MovieItem

data class MovieItemResponse(
	val overview: String? = null,
	val posterUrl: String? = null,
	val releaseDate: String? = null,
	val id: Int? = null,
	val title: String? = null,
	val filmRate: String? = null,
	val director: String? = null,
	val trailerUrl: String? = null,
	val videoUrl: String? = null,
	val category: List<String>? = null,
	val cast: List<String>? = null,
	val isUserWatchList: Boolean? = null
)

fun MovieItemResponse.toDomain() = MovieItem(
	this.overview,
	this.posterUrl,
	this.releaseDate,
	this.id,
	this.title,
	this.filmRate,
	this.director,
	this.trailerUrl,
	this.videoUrl,
	this.category,
	this.cast,
	this.isUserWatchList
)

