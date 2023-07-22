package abika.sinau.mymoviedb.utils.base

abstract class BaseSuspendUseCase<Q : BaseSuspendUseCase.RequestValues, P : BaseSuspendUseCase.ResponseValues> {

    abstract suspend fun execute(requestValues: Q): P

    interface RequestValues

    interface ResponseValues
}