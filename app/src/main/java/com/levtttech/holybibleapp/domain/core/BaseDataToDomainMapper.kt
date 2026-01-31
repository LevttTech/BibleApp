package com.levtttech.holybibleapp.domain.core

import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.core.ErrorType
import retrofit2.HttpException
import java.net.UnknownHostException
abstract class BaseDataToDomainMapper<S, R> : Abstract.Mapper.DataToDomain<S, R> {
    protected fun errorType(e: Exception) = when (e) {
        is UnknownHostException -> ErrorType.NO_CONNECTION
        is HttpException -> ErrorType.SERVICE_UNAVAILABLE
        else -> ErrorType.GENERIC_ERROR
    }
}