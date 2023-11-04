package com.example.api.exception

import java.lang.RuntimeException

class CustomException(val customErrorCode: ErrorCode) : RuntimeException() {
    override fun toString():String {
        return "" + customErrorCode.statusCode + " " +
                customErrorCode.errorCode + " " +
                customErrorCode.errorMessage
    }
}