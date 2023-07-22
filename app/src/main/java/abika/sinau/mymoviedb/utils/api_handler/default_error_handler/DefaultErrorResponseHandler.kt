package abika.sinau.mymoviedb.utils.api_handler.default_error_handler

import abika.sinau.mymoviedb.utils.api_handler.ErrorResponseHandler
import abika.sinau.mymoviedb.utils.exception.BadRequestException
import abika.sinau.mymoviedb.utils.exception.HandleableException
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody


class DefaultErrorResponseHandler : ErrorResponseHandler() {
    override fun handleBadRequestError(errorBody: ResponseBody): HandleableException {
        val type = object : TypeToken<DefaultErrorResponse>() {}.type
        val errorWrapper = errorBody.errorBodyParser<DefaultErrorResponse>(type)
        val errorMessage = errorWrapper?.message
        return BadRequestException(errorMessage.orEmpty())
    }
}