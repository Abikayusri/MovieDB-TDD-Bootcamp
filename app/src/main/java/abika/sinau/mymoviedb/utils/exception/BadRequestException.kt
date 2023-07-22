package abika.sinau.mymoviedb.utils.exception

/**
 * BadRequestException is thrown when api response code is 400
 * @param message The message from error response payload
 * @param message is nullable
 * @param message if null, it must be set with default message in ui layer
 */
class BadRequestException(
    override val message: String? = null,
    val messageType: String = "",
    val responseCode: Int = 0
) : Exception(message)