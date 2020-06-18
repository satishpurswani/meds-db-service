package com.poc.dbserver.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LoggingAdvice {

	private final Logger LOGGER = LoggerFactory.getLogger(LoggingAdvice.class);

	@Pointcut(value = "execution(* com.poc.dbserver.controller.impl.*.*(..) )")
	public void myPointcut() {

	}

	@Around("myPointcut()")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		LOGGER.info("ClassName : " + pjp.getTarget().getClass().toString() + ", MethodName : "
				+ pjp.getSignature().getName() + "(), Arguments : "
				+ new ObjectMapper().writeValueAsString(pjp.getArgs()));
		
		Object obj = pjp.proceed();
		
		LOGGER.info("ClassName : " + pjp.getTarget().getClass().toString() + ", MethodName : "
				+ pjp.getSignature().getName() + "(), Response : " + new ObjectMapper().writeValueAsString(obj));
		return obj;

	}
}
