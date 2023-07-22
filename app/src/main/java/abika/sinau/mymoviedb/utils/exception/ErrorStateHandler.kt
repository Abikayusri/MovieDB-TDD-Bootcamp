package abika.sinau.mymoviedb.utils.exception

import java.net.UnknownHostException

object ErrorStateHandler {

    fun handleErrorState(
        throwable: Throwable,
        onHandleableException: () -> Unit,
        onGeneralException: (error: Throwable) -> Unit,
    ) {
        when (throwable) {
            is UnknownHostException -> onHandleableException.invoke()
            is NoConnectivityException -> onHandleableException.invoke()
            is BadRequestException -> onGeneralException(throwable)
            is ServerErrorException -> onGeneralException(throwable)
            else -> onGeneralException(throwable)
        }
    }
}