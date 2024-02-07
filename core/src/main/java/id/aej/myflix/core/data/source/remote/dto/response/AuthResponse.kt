package id.aej.myflix.core.data.source.remote.dto.response

data class AuthResponse(
	val user: User? = null,
	val token: String? = null
)

data class Detail(
	val gender: String? = null,
	val watchList: List<String>? = null,
	val birthDate: String? = null,
	val email: String? = null,
	val username: String? = null
)

data class User(
	val id: String? = null,
	val detail: Detail? = null
)
