package id.aej.myflix.core.domain.model

data class MovieItem(
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

