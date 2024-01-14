package id.aej.myflix.core.data.source.remote.dto.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("token")
	val token: String? = null
)

data class Detail(

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("watchList")
	val watchList: List<String>? = null,

	@field:SerializedName("birthDate")
	val birthDate: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class User(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("detail")
	val detail: Detail? = null
)
