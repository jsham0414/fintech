package com.example.kafka.dto

data class LoanRequestDto(
    val userKey: String,
    val userName: String,               // 유저명
    val userIncomeAmount: Long,         // 소득 금액
    var userRegistrationNumber: String  // 주민번호
)