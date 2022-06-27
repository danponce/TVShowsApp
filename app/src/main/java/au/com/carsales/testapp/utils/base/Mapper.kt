package au.com.carsales.testapp.utils.base

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer layers
 *
 * @param <V> the view model input type
 * @param <D> the domain model output type
 */
interface Mapper<in Source, out Destination> {

    fun executeMapping(type: Source?): Destination?

}