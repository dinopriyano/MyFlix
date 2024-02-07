package id.aej.myflix.core.data.source.remote.dto.response

data class WebResponse<T> (
	val data: T? = null,
	val success: Boolean? = null,
	val message: Any? = null,
	val statusCode: Int? = null,
	val error: String? = null,
)