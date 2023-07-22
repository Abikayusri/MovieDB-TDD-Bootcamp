package abika.sinau.mymoviedb.utils.base

abstract class BaseUseCase<REQUEST : BaseUseCase.RequestValues, RESPONSE : BaseUseCase.ResponseValues> {

    abstract fun execute(request: REQUEST): RESPONSE

    interface RequestValues

    interface ResponseValues

}