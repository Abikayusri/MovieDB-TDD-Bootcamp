package abika.sinau.mymoviedb.domain.model.request

data class MovieListQuery(
    var page: Int = 1,
) {
    fun toMap(): Map<String, Any> {
        val queryMap = HashMap<String, Any>()
        queryMap["page"] = page
        return queryMap
    }
}
