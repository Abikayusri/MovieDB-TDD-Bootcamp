package abika.sinau.mymoviedb.utils.base

import abika.sinau.mymoviedb.utils.exception.ErrorStateHandler
import abika.sinau.mymoviedb.utils.exception.error_state_handler.ErrorHandlerProviderImpl
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding, VM : ViewModel> : AppCompatActivity() {

    abstract val viewModel: VM

    private val errorHandlerProvider = ErrorHandlerProviderImpl()

    protected lateinit var binding: VB
    abstract val bindingInflater: (LayoutInflater) -> VB

    protected abstract fun setupViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingInflater.invoke(LayoutInflater.from(this))
        setContentView(binding.root)
        setupViews()
    }


    protected fun handleErrorApiState(
        throwable: Throwable,
        anchor: View? = null,
        onHandleableError: () -> Unit
    ) {
        ErrorStateHandler.handleErrorState(
            throwable = throwable,
            onHandleableException = onHandleableError,
            onGeneralException = {
                errorHandlerProvider.generalError(
                    this,
                    lifecycle,
                    throwable = throwable,
                    anchor
                )
            }
        )
    }
}