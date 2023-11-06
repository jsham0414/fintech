package com.example.api.loan.request

import com.example.api.loan.KeyGenerator
import com.example.api.loan.encrypt.EncryptComponent
import com.example.domain.domain.UserInfo
import com.example.domain.repository.UserInfoRepository
import com.example.kafka.producer.LoanRequestSender
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@WebMvcTest(LoanRequestController::class)
internal class LoanRequestControllerTest {
    private lateinit var mockMvc: MockMvc

    private lateinit var loanRequestController: LoanRequestController
    private lateinit var keyGenerator: KeyGenerator
    private lateinit var encryptComponent: EncryptComponent

    @MockBean
    private lateinit var loanRequestSender: LoanRequestSender

    private val userInfoRepository: UserInfoRepository = mockk()

    @MockBean
    private lateinit var loanRequestServiceImpl: LoanRequestServiceImpl

    private lateinit var mapper: ObjectMapper

    companion object {
        private const val baseUrl = "/fintech/api/v1"
    }

    @BeforeEach
    fun init() {
        keyGenerator = KeyGenerator()
        encryptComponent = EncryptComponent()


        loanRequestServiceImpl = LoanRequestServiceImpl(
            keyGenerator, userInfoRepository, encryptComponent, loanRequestSender
        )

        loanRequestController = LoanRequestController(loanRequestServiceImpl)

        mockMvc = MockMvcBuilders.standaloneSetup(loanRequestController).build()

        // 기본 생성자가 없다는 오류를
        mapper = ObjectMapper().registerModule(KotlinModule.Builder().build())
    }

    @Test
    @DisplayName("유저 요청이 들어오면 정상 응답을 준다.")
    fun loanRequestSuccess() {
        // given
        val loanRequestInputDto: LoanRequestDto.LoanRequestInputDto =
            LoanRequestDto.LoanRequestInputDto(
                userName = "TEST",
                userIncomeAmount = 10000,
                userRegistrationNumber = "000101-980512"
            )

        every {
            userInfoRepository.save(any())
        } returns UserInfo("", "", "", 1)

        // when
        // then
        mockMvc.post(
            "$baseUrl/request"
        ) {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = mapper.writeValueAsString(loanRequestInputDto)
        }.andExpect {
            status {
                isOk()
            }
        }

    }
}