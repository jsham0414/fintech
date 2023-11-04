package com.example.api.loan

import org.springframework.stereotype.Component
import java.util.*

@Component
class KeyGenerator {
    fun generateUserKey() = UUID.randomUUID().toString().replace("-", "");
}