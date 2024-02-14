package id.aej.myflix.core.data.source.remote.dto.response

import id.aej.myflix.core.domain.model.Auth
import id.aej.myflix.core.domain.model.Detail
import id.aej.myflix.core.domain.model.User

data class AuthResponse(
	val user: UserResponse? = null,
	val token: String? = null
)

data class DetailResponse(
	val gender: String? = null,
	val watchList: List<String>? = null,
	val birthDate: String? = null,
	val email: String? = null,
	val username: String? = null
)

data class UserResponse(
	val id: String? = null,
	val detail: DetailResponse? = null
)

fun AuthResponse.toDomain(): Auth = Auth(
	User(
		this.user?.id,
		Detail(
			this.user?.detail?.gender,
			this.user?.detail?.watchList,
			this.user?.detail?.birthDate,
			this.user?.detail?.email,
			this.user?.detail?.username
		)
	),
	this.token
)
