package abika.sinau.mymoviedb.utils.api_handler

import abika.sinau.mymoviedb.utils.exception.ServerErrorException
import abika.sinau.mymoviedb.utils.exception.HandleableException
import com.google.gson.Gson
import okhttp3.ResponseBody
import java.lang.reflect.Type

abstract class ErrorResponseHandler {

    fun handle(errorBody: ResponseBody?, responseCode: Int): Exception {
        return fetchError(errorBody, responseCode)
    }

    protected open fun fetchError(errorBody: ResponseBody?, responseCode: Int): Exception {
        return try {
            val exception = when (responseCode) {
                in 400..404 -> {
                    errorBody?.let { error ->
                        handleBadRequestError(error)
                    } ?: Exception()
                }

                in 500..599 -> ServerErrorException()
                else -> Exception()
            }
            exception
        } catch (exception: Exception) {
            exception
        }
    }

    protected abstract fun handleBadRequestError(errorBody: ResponseBody): HandleableException

    protected fun <T> ResponseBody.errorBodyParser(type: Type): T? {
        return this.string().let {
            Gson().fromJson(it, type)
        }
    }
}