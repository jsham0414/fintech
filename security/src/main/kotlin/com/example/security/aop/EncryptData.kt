package com.example.security.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Aspect
annotation class EncryptData

private val encryptString = EncryptString()

@Pointcut("execution(* com.example.)")
private fun isEncrypt() = 0

@Before("isEncrypt()")
fun encrypt(joinPoint: ProceedingJoinPoint): Any {
    val normal = joinPoint.args[0].toString();
    return encryptString.encryptString(normal)
}

@Pointcut("@annotation(com.example.security.aop.DecryptData)")
private fun isDecrypt() = 0

@Before("isDecrypt()")
fun decrypt(joinPoint: ProceedingJoinPoint): String {
    val encrypted = joinPoint.args[0].toString();
    return encryptString.decryptString(encrypted)
}


