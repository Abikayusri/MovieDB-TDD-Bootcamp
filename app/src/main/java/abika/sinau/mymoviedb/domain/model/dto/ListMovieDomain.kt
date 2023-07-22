package abika.sinau.mymoviedb.domain.model.dto

data class ListMovieDomain(
    val id: Int,
    val title: String,
    val posterPath: String,
    val overview: String,
    val voteCount: Double
)
