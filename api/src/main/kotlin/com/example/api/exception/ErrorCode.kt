package com.example.api.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val statusCode: HttpStatus,
    val errorCode: String,
    val errorMessage: String
) {
    RESULT_NOT_FOUND(HttpStatus.BAD_REQUEST, "E001", "result not found")

}