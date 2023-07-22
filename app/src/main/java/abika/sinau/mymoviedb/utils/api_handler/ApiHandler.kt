package abika.sinau.mymoviedb.utils.api_handler

import abika.sinau.mymoviedb.utils.api_handler.default_error_handler.DefaultErrorResponseHandler
import retrofit2.Response

class ApiHandler {
    companion object {
        suspend fun <T : Any> handleApi(
            errorHandler: ErrorResponseHandler = DefaultErrorResponseHandler(),
            block: suspend () -> Response<T>
        ): T? {
            try {
                val response = block.invoke()

                if (response.isSuccessful) {
                    return response.body()
                }

                // When Error
                throw errorHandler.handle(response.errorBody(), response.code())
            } catch (e: Exception) {
                throw e
            }
        }
    }
}