package com.example.api.loan.request

interface LoanRequestService {
    fun loanRequestMain(loanRequestInputDto: LoanRequestDto.LoanRequestInputDto): LoanRequestDto.LoanRequestResponseDto
    fun saveUserInfo(userInfoDto: UserInfoDto)
    fun loanRequestReview(userInfoDto: UserInfoDto)
}