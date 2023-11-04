package com.example.api.loan.request

class LoanRequestDto {
    data class LoanRequestInputDto(
        val userName: String,               // 유저명
        val userIncomeAmount: Long,         // 소득 금액
        var userRegistrationNumber: String  // 주민번호
    ) {
        fun toUserInfoDto(userKey: String) = UserInfoDto(
            userKey, userName, userRegistrationNumber, userIncomeAmount
        )
    }

    data class LoanRequestResponseDto(
        val userKey: String
    )
}