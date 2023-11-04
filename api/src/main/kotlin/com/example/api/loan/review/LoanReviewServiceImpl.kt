package com.example.api.loan.review

import com.example.api.exception.CustomException
import com.example.api.exception.ErrorCode
import com.example.domain.domain.LoanReview
import com.example.domain.repository.LoanReviewRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class LoanReviewServiceImpl(
    private val loanReviewRepository: LoanReviewRepository
) : LoanReviewService {
    override fun loanReviewMain(userKey: String): LoanReviewDto.LoanReviewResponseDto {
        val loanResult = getLoanResult(userKey)

        return LoanReviewDto.LoanReviewResponseDto(
            userKey = userKey,
            loanResult = getLoanResult(userKey)?.toResponseDto()?:throw CustomException(ErrorCode.RESULT_NOT_FOUND)
        )
    }

    @Cacheable(value = ["REVIEW"], key = "#userKey", cacheManager = "redisCacheManager")
    override fun getLoanResult(userKey: String) = loanReviewRepository.findByUserKey(userKey)

    private fun LoanReview.toResponseDto() =
        LoanReviewDto.LoanResult(
            userLimitAmount = this.loanLimitedAmount,
            userLoanInterest = this.loanInterest
        )

}