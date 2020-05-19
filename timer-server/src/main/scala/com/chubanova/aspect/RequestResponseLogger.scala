package com.chubanova.aspect

import com.typesafe.scalalogging.LazyLogging

import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component


@Component
@Aspect
class RequestResponseLogger extends LazyLogging{

  import java.util

  import org.aspectj.lang.ProceedingJoinPoint
  import org.aspectj.lang.annotation.Around

  @Around("execution(* com.chubanova.grpc.TimerService.*(..))")
  @throws[Throwable]
  def logService(joinPoint: ProceedingJoinPoint): Any = {
    logger.info("CALL SERVICE METHOD: " + joinPoint.getSignature.getName + " WITH REQUEST: " + util.Arrays.toString(joinPoint.getArgs))
    val proceed = joinPoint.proceed
    logger.info( "SERVICE RESULT IS: " + proceed)
    proceed
  }
}
