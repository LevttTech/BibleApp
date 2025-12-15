package com.levtttech.bibleapp.core

import com.levtttech.bibleapp.domain.books.ErrorType
import com.levtttech.bibleapp.presentation.books.ResourceProvider
import java.net.HttpRetryException
import java.net.UnknownHostException

abstract class Abstract {

    interface Object<T, M : Mapper> {
        fun map(mapper: M): T
    }

    interface Mapper {
        interface Data<S, R> : Mapper {
            fun map(data: S): R
        }

        interface DataToDomain<S, R> : Data<S, R> {
            fun map(e: Exception): R

            abstract class Base<S, R> : DataToDomain<S, R> {
                protected fun errorType(e: Exception) = when (e) {
                    is UnknownHostException -> ErrorType.NO_CONNECTION
                    is HttpRetryException -> ErrorType.SERVICE_UNAVAILABLE
                    else -> ErrorType.GENERIC_ERROR
                }
            }

        }

        interface DomainToUi<S, R> : Data<S, R> {

            fun map(e: ErrorType): R

            abstract class Base<S, R>(
                private val resourceProvider: ResourceProvider,
            ) : DomainToUi<S, R> {
                protected fun uiError(e: ErrorType) = when (e) {
                    ErrorType.NO_CONNECTION -> resourceProvider.getString(com.levtttech.bibleapp.R.string.no_connection_message)
                    ErrorType.SERVICE_UNAVAILABLE -> resourceProvider.getString(com.levtttech.bibleapp.R.string.service_unavailable_message)
                    ErrorType.GENERIC_ERROR -> resourceProvider.getString(com.levtttech.bibleapp.R.string.generic_error_message)
                }
            }
        }


        class Empty : Mapper

    }
}

