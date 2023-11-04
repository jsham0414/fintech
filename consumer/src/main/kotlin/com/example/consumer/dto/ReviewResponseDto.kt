package com.example.consumer.dto

import com.example.domain.domain.LoanReview

data class ReviewResponseDto(
    val userKey: String,
    val limitedAmount: Long,
    val interest: Double
) {
    fun toLoanReviewEntity(): LoanReview =
        LoanReview(
            userKey = userKey,
            loanInterest = interest,
            loanLimitedAmount = limitedAmount
        )
}