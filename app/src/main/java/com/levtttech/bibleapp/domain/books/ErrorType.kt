package com.levtttech.bibleapp.domain.books

import java.net.HttpRetryException
import java.net.UnknownHostException

enum class ErrorType {
    NO_CONNECTION, SERVICE_UNAVAILABLE, GENERIC_ERROR
}
