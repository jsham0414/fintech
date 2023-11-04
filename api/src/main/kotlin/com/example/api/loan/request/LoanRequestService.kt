package com.example.api.loan.request

import org.springframework.stereotype.Service

interface LoanRequestService {
    fun loanRequestMain(loanRequestInputDto: LoanRequestDto.LoanRequestInputDto): LoanRequestDto.LoanRequestResponseDto
    fun saveUserInfo(userInfoDto: UserInfoDto)
    fun loanRequestReview(userInfoDto: UserInfoDto)
}