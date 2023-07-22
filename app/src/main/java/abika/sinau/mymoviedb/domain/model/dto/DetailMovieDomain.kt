package abika.sinau.mymoviedb.domain.model.dto

data class DetailMovieDomain(
    val id: Int,
    val title: String,
    val posterPath: String,
    val overview: String,
    val voteCount: Double,
    val backdropPath: String,
    val releaseDate: String,
)
