package com.galaxe.AjioClone.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class GeneralAspect {

	@Pointcut("execution(* com.galaxe.AjioClone.controller .*.*(..))")
	public void loggingPointCutController() {
	}

	@Pointcut("execution(* com.galaxe.AjioClone.repository .*.*(..))")
	public void loggingPointCutRepository() {
	}

	@Pointcut("execution(* com.galaxe.AjioClone.exceptions .*.*(..))")
	public void loggingPointCutExceptions() {
	}

	@Pointcut("execution(* com.galaxe.AjioClone.serviceImpl .*.*(..))")
	public void loggingPointCutService() {

	}

//  First type

	/* Before- Logging used for controller */
	
	@Before("loggingPointCutController()")
	public Boolean beforeForController(JoinPoint joinpoint) {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = joinpoint.getSignature().getName();
		String className = joinpoint.getTarget().getClass().toString();
		Object[] argsObjects = joinpoint.getArgs();
		try {
			log.info("method name called : " + methodName + "()" + " class>> " + className + ", Arguments : "
					+ mapper.writeValueAsString(argsObjects));
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		return true;
	}

	/* Before-  Logging used for Service */
	
	@Before("loggingPointCutService ()")
	public Boolean beforeForService(JoinPoint joinpoint) {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = joinpoint.getSignature().getName();
		String className = joinpoint.getTarget().getClass().toString();
		Object[] argsObjects = joinpoint.getArgs();
		try {
			log.info("Befor Log from the service : " + " method name called : " + methodName + "()" + " class>> "
					+ className + ", Arguments : " + mapper.writeValueAsString(argsObjects));
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		return true;
	}

	/* Before-  Logging used for repository */
	
	@Before("loggingPointCutRepository()")
	public Boolean beforeForRepository(JoinPoint joinpoint) {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = joinpoint.getSignature().getName();
		Object[] argsObjects = joinpoint.getArgs();
		try {
			log.info("Befor Log from the repository : " + " method name called : " + methodName + "()"
					+ ", Arguments : " + mapper.writeValueAsString(argsObjects));
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		return true;
	}

	/* After-  Logging used for controller */
	
	@After("loggingPointCutController()")
	public Boolean afterForController(JoinPoint joinpoint) {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = joinpoint.getSignature().getName();
		String className = joinpoint.getTarget().getClass().toString();
		Object[] argsObjects = joinpoint.getArgs();
		try {
			log.info("method name called : " + methodName + "(), " + " class>> : " + className + ", Arguments : "
					+ mapper.writeValueAsString(argsObjects));
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		return true;
	}
	
	/* After-  Logging used for Service */

	@After("loggingPointCutService()")
	public Boolean afterForService(JoinPoint joinpoint) {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = joinpoint.getSignature().getName();
		String className = joinpoint.getTarget().getClass().toString();
		Object[] argsObjects = joinpoint.getArgs();
		try {
			log.info("After Log from the service : " + " method name called : " + methodName + "(), " + " class>> : "
					+ className + ", Arguments : " + mapper.writeValueAsString(argsObjects));
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		return true;
	}

	/* After-  Logging used for repository */
	
	@After("loggingPointCutRepository()")
	public Boolean afterForRepository(JoinPoint joinpoint) {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = joinpoint.getSignature().getName();

		Object[] argsObjects = joinpoint.getArgs();
		try {
			log.info("After Log from the repository : " + " method name called : " + methodName + "(), "
					+ ", Arguments : " + mapper.writeValueAsString(argsObjects));
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		return true;
	}

	@AfterReturning(value = "loggingPointCutController()", returning = "obj")
	public Boolean afterReturning(JoinPoint joinpoint, Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = joinpoint.getSignature().getName();
		try {
			log.info("Method Name : " + methodName + ", Arguments : " + mapper.writeValueAsString(obj));
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		return true;
	}

	@AfterThrowing(value = "loggingPointCutController()", throwing = "e")
	public void afterThrowing(JoinPoint joinpoint, Throwable e) {
		log.error("Execution in class ", joinpoint.getSignature().getDeclaringTypeName()
				+ joinpoint.getSignature().getName() + " message : " + e.getMessage());
	}

//	Second Type

//	@AfterReturning(value = "loggingPointCut()",
//			returning = "productDetails")
//	public void after(JoinPoint joinpoint, ProductDetails productDetails) {
//		log.info("After method invoked ::" + productDetails);
//	}
//	
//	@AfterThrowing(value = "loggingPointCut()",
//			throwing =  "e")
//	public void after(JoinPoint joinpoint, Exception e) {
//		log.info("After method invoked ::" + e.getMessage());
//	}

//	Third Type

//	@Around("loggingPointCut()")
//	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//		log.info("Before method invoked :: " + proceedingJoinPoint.getArgs()[0]);
//		Object object = proceedingJoinPoint.proceed();
//		log.info("after method invoked :: "+ proceedingJoinPoint.getArgs()[0]);
//		
//		if(object instanceof ProductDetails) {
//			log.info("after method invoked :: "+ proceedingJoinPoint.getSignature());
//		}
//		
//		return object;
}
