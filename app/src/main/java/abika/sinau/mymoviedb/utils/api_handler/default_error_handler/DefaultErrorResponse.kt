package abika.sinau.mymoviedb.utils.api_handler.default_error_handler

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class DefaultErrorResponse(
    @Expose
    @SerializedName("status")
    val status: String? = null,

    @Expose
    @SerializedName("message")
    val message: String? = null
)
