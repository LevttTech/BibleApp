package com.levtttech.holybibleapp.presentation.core

import com.levtttech.bibleapp.R
import com.levtttech.holybibleapp.core.Abstract
import com.levtttech.holybibleapp.core.ResourceProvider
import com.levtttech.holybibleapp.core.ErrorType

abstract class BaseDomainToUiMapper<S, T>(private val resourceProvider: ResourceProvider) :
    Abstract.Mapper.DomainToUi<S, T> {

    protected fun errorMessage(errorType: ErrorType) = resourceProvider.string(
        when (errorType) {
            ErrorType.NO_CONNECTION -> R.string.no_connection_message
            ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
            else -> R.string.something_went_wrong
        }
    )
}