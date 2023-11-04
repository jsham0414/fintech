package com.example.css.service

import com.example.css.dto.LoanRequestDto
import com.example.css.dto.LoanResultDto
import org.springframework.stereotype.Service

@Service
class LoanReviewService {
    fun loanReview(requestInputDto: LoanRequestDto.RequestInputDto): LoanResultDto.ResponseDto {
        if (requestInputDto.userIncomeAmount < 0) throw RuntimeException("Invalid userIncomeAmount Param")
        if (requestInputDto.userIncomeAmount < 10000000) return LoanResultDto.ResponseDto(requestInputDto.userKey, 10000000, 0.0)
        if (requestInputDto.userIncomeAmount < 20000000) return LoanResultDto.ResponseDto(requestInputDto.userKey, 20000000, 10.0)
        if (requestInputDto.userIncomeAmount < 30000000) return LoanResultDto.ResponseDto(requestInputDto.userKey, 30000000, 9.0)
        if (requestInputDto.userIncomeAmount < 40000000) return LoanResultDto.ResponseDto(requestInputDto.userKey, 40000000, 8.0)
        if (requestInputDto.userIncomeAmount < 50000000) return LoanResultDto.ResponseDto(requestInputDto.userKey, 50000000, 7.0)
        if (requestInputDto.userIncomeAmount >= 50000000) return LoanResultDto.ResponseDto(requestInputDto.userKey, 60000000, 6.0)
        throw RuntimeException("Invalid userIncomeAmount Param")

    }
}