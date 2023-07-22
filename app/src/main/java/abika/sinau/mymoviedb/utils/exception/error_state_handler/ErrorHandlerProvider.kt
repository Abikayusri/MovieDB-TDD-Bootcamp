package abika.sinau.mymoviedb.utils.exception.error_state_handler

import android.app.Activity
import android.view.View
import androidx.lifecycle.Lifecycle

interface ErrorHandlerProvider {
    fun generalError(activity: Activity, lifecycle: Lifecycle, throwable: Throwable, anchor: View?)
}