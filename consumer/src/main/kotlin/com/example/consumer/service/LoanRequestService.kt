package com.example.consumer.service

import com.example.consumer.dto.ReviewResponseDto
import com.example.domain.domain.LoanReview
import com.example.domain.repository.LoanReviewRepository
import com.example.kafka.dto.LoanRequestDto
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.postForEntity
import java.time.Duration

@Service
class LoanRequestService(
    private val loanReviewRepository: LoanReviewRepository
) {
    companion object {
        // 많아지면 따로 모듈을 만들어서 외부랑 통신하는 Rest Template나 Web Client만 모아두기
        const val cssUrl = "http://localhost:8081/css/api/v1/request"
    }

    fun loanRequest(loanRequestDto: LoanRequestDto) {
        // TODO : CB Component로 요청 보내기 -> 응답값을 DB에 저장하기 Spring Web Client
        val resultResult = loanRequestToCb(loanRequestDto)

        saveLoanReviewData(resultResult.toLoanReviewEntity())
    }

    fun loanRequestToCb(loanRequestDto: LoanRequestDto): ReviewResponseDto {
        // TODO :
        val restTemplate = RestTemplateBuilder()
            .setConnectTimeout(Duration.ofMillis(1000))
            .setReadTimeout(Duration.ofMillis(1000))
            .build()

        return restTemplate.postForEntity(cssUrl, loanRequestDto, ReviewResponseDto::class.java).body!!
    }

    fun saveLoanReviewData(loanReview: LoanReview) = loanReviewRepository.save(loanReview)

}