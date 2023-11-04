package com.example.css.dto

class LoanResultDto {
    data class ResponseDto(
        val userKey: String,
        val limitedAmount: Long,
        val interest: Double
    )

}