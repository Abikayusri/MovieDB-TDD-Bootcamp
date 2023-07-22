package abika.sinau.mymoviedb.utils.exception

import abika.sinau.core.utils.handle_exception.NoConnectivityException
import abika.sinau.core.utils.handle_exception.ServerErrorException
import abika.sinau.mymoviedb.R
import abika.sinau.mymoviedb.utils.toastLong
import android.app.Activity
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorStateHandler {

    fun handleErrorState(
        activity: Activity,
        throwable: Throwable,
    ) {
        with(activity) {
            when (throwable) {
                is UnknownHostException -> toastLong(getString(R.string.no_internet_error_message))
                is SocketTimeoutException -> toastLong(getString(R.string.no_internet_error_message))
                is NoConnectivityException -> toastLong(getString(R.string.no_internet_error_message))
                is BadRequestException -> toastLong(getString(R.string.something_went_wrong_message))
                is ServerErrorException -> toastLong(getString(R.string.something_went_wrong_try_again_message))
                else -> toastLong(getString(R.string.something_went_wrong_try_again_message))
            }
        }
    }
}