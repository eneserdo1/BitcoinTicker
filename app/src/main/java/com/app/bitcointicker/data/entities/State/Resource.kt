package com.app.bitcointicker.data.entities.State

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

data class Resource<out T>(val status: Status, val data: T?,val error:Error?, val message: String?) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null,null)
        }

        fun <T> error(msg: String,error: Error?): Resource<T> {
            return Resource(Status.ERROR, null,error, msg)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null,null)
        }

    }

}