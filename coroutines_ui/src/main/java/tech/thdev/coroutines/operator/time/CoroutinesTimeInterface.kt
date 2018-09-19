package tech.thdev.coroutines.operator.time

interface CoroutinesTimeInterface {

    /**
     * Next click event
     */
    suspend fun hasNext(): Boolean
}