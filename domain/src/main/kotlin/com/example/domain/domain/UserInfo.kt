package com.example.domain.domain

import jakarta.persistence.*

@Entity
@Table(name = "USER_INFO")
class UserInfo(
    @Column(name = "user_key")
    val userKey: String,

    @Column(name = "user_reg_num")
    val userRegistrationNumber: String,

    @Column(name = "user_name")
    val userName: String,

    @Column(name = "user_icn_amt")
    val userIncomeAmount: Long
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}