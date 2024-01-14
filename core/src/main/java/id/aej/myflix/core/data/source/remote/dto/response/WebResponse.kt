package id.aej.myflix.core.data.source.remote.dto.response

import com.google.gson.annotations.SerializedName

data class WebResponse<T> (

	@field:SerializedName("data")
	val data: T? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: Any? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null,

	@field:SerializedName("error")
	val error: String? = null,
)