package abika.sinau.mymoviedb.domain.model.response

import com.google.gson.annotations.SerializedName

open class PagingBaseResponse<T> {
    @SerializedName("total_pages")
    val totalPages: Int? = null
    @SerializedName("results")
    val results: List<T>? = null
    @SerializedName("page")
    val page: Int? = null
}
