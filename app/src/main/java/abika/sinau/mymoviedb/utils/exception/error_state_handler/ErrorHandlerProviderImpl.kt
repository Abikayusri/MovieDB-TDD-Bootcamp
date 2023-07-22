package abika.sinau.mymoviedb.utils.exception.error_state_handler

import abika.sinau.mymoviedb.utils.exception.ServerErrorException
import abika.sinau.mymoviedb.utils.toastShort
import android.app.Activity
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorHandlerProviderImpl : ErrorHandlerProvider {

    companion object {
        private const val JOB_DELAY = 1000L
    }

    private var toastJob: Job? = null
    private var logoutJob: Job? = null

    override fun generalError(
        activity: Activity,
        lifecycle: Lifecycle,
        throwable: Throwable,
        anchor: View?
    ) = with(activity) {
        when (throwable) {
            is UnknownHostException -> safeErrorMessage(
                this,
                lifecycle,
                anchor,
                "Jaringan tidak stabil, silahkan periksa pengaturan jaringan"
            )

            is SocketTimeoutException -> safeErrorMessage(
                this,
                lifecycle,
                anchor,
                "Jaringan tidak stabil, silahkan periksa pengaturan jaringan"
            )

            is ServerErrorException -> safeErrorMessage(
                this,
                lifecycle,
                anchor,
                "Terjadi kesalahan, silahkan coba beberapa saat lagi"
            )

            else -> safeErrorMessage(
                this,
                lifecycle,
                anchor,
                "Terjadi kesalahan, silahkan coba beberapa saat lagi"
            )
        }
    }

    private fun safeErrorMessage(
        activity: Activity,
        lifecycle: Lifecycle,
        anchor: View?,
        message: String
    ) {
        lifecycle.coroutineScope.launch {
            toastJob?.cancel()
            toastJob = launch {
                delay(JOB_DELAY)
                activity.toastShort(message)
            }
        }
    }
}