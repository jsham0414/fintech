package com.example.domain.security

import com.example.domain.domain.UserInfo
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component
import java.lang.reflect.Field
import java.util.*

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Encrypt

@Aspect
@Component
class EncryptData {
    private val encryptString = EncryptString()

    @Pointcut("execution(* com.example.domain.repository.*.save(*))")
    private fun isSave() {}

    @Around("isSave()")
    fun encrypt(joinPoint: ProceedingJoinPoint): Any {
        val target = joinPoint.args[0]
        val fields: Array<Field> = target.javaClass.declaredFields
        for (field in fields) {
            if (field.isAnnotationPresent(Encrypt::class.java)) {
                try {
                    field.isAccessible = true

                    val data = field.get(target)
                    var stringData: String = ""
                    if (data is String) {
                        stringData = data
                    }

                    val encryptedData: String = encryptString.encryptString(stringData)
                    field.set(target, encryptedData)
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }
            }
        }

        joinPoint.args[0] = target
        return joinPoint.proceed(joinPoint.args)
    }

    @Pointcut("execution(* com.example.domain.repository.*.*find*(*))")
    private fun isFind() {}

    @Around("isFind()")
    fun decrypt(joinPoint: ProceedingJoinPoint): Any {
        // 옵셔널
        val targetData = joinPoint.proceed(joinPoint.args)
        var targetOpt: Optional<*>? = null
        if (targetData is Optional<*>)
            targetOpt = targetData

        // 널이면 그냥 원래 데이터 주기
        val target: Any = targetOpt?.get() ?: return targetData

        // 이제부터 UserInfo
        val fields: Array<Field> = target.javaClass.declaredFields
        for (field in fields) {

            if (field.isAnnotationPresent(Encrypt::class.java)) {
                try {
                    field.isAccessible = true

                    val data = field.get(target)
                    var stringData: String = ""
                    if (data is String) {
                        stringData = data
                    }

                    val plainData: String = encryptString.decryptString(stringData)
                    field.set(target, plainData)
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }
            }
        }

        return target;
    }
}

