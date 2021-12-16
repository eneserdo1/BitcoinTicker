package com.app.bitcointicker.data.remoteDataSource

import com.app.bitcointicker.data.entities.State.Resource
import retrofit2.Response

abstract class DataSource {

    open suspend fun  <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Resource<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return Resource.success(result.body())
            } else {
                Resource.error(defaultErrorMessage, null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.error("Error ${e.message}", null)
        }
    }
}