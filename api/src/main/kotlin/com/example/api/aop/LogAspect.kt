package com.example.api.aop

import mu.KotlinLogging
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component
import org.springframework.util.ObjectUtils
import org.springframework.util.StopWatch
import kotlin.text.StringBuilder

@Aspect
@Component
class LogAspect {
    val logger = KotlinLogging.logger {}

    @Pointcut("within(com.example.api..*)")
    private fun isApi() {

    }

    @Around("isApi()")
    fun loggingAspect(joinPoint: ProceedingJoinPoint): Any {
        val stopWatch = StopWatch()
        stopWatch.start()

        val result = joinPoint.proceed()
        stopWatch.stop()

        val sb = StringBuilder()
        joinPoint.args.forEach { s ->
            run {
                if (s != null)
                    sb.append(s.toString()).append(" ")
            }
        }

        logger.info {"${joinPoint.signature.name} [${sb.dropLast(1)}] ${stopWatch.lastTaskTimeMillis}"}
        return result;
    }
}