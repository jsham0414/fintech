package com.example.api.loan.request

import com.example.api.loan.KeyGenerator
import com.example.domain.repository.UserInfoRepository
import com.example.kafka.enum.KafkaTopic
import com.example.kafka.producer.LoanRequestSender
import org.springframework.stereotype.Service

@Service
class LoanRequestServiceImpl(
    private val keyGenerator: KeyGenerator,
    private val userInfoRepository: UserInfoRepository,
    private val loanRequestSender: LoanRequestSender
) : LoanRequestService {
    override fun loanRequestMain(
        loanRequestInputDto: LoanRequestDto.LoanRequestInputDto
    ): LoanRequestDto.LoanRequestResponseDto {
        val userKey = keyGenerator.generateUserKey()
        val userInfoDto = loanRequestInputDto.toUserInfoDto(userKey)

        saveUserInfo(userInfoDto)
        loanRequestReview(userInfoDto)

        return LoanRequestDto.LoanRequestResponseDto(userKey)
    }

    override fun saveUserInfo(userInfoDto: UserInfoDto) {
        userInfoRepository.save(userInfoDto.toEntity())
    }

    override fun getUserInfo(userKey: String): UserInfoDto {
        val info = userInfoRepository.findByUserKey(userKey)
        return UserInfoDto(
            userKey = info.userKey,
            userRegistrationNumber = info.userRegistrationNumber,
            userIncomeAmount = info.userIncomeAmount,
            userName = info.userName
        )
    }

    override fun loanRequestReview(userInfoDto: UserInfoDto) {
        loanRequestSender.sendMessage(
            KafkaTopic.LOAN_REQUEST,
            userInfoDto.toLoanRequestKafkaDto()
        )
    }

}