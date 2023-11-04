package com.example.css.dto

class LoanRequestDto {
    data class RequestInputDto(
        val userKey: String,
        val userName: String,               // 유저명
        val userIncomeAmount: Long,         // 소득 금액
        var userRegistrationNumber: String  // 주민번호
    )

}
