package com.cafe24.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionTimeAspect {
            	     //* * 반환타입 전부	 //*.* 모든 클래스의 모든 메서드
	@Around("execution(* *..repository.*.*(..)) || execution(* *..service.*.*(..))|| execution(* *..controller.*.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		// before
		StopWatch sw = new StopWatch();
		sw.start();

		// method 실행
		Object result = pjp.proceed();

		// after
		sw.stop();
		Long totalTime = sw.getTotalTimeMillis();
		// around 하고있는 메서드의 이름을 출력한다.
		String classname = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		String taskName = classname + "." + methodName;
//		System.out.println("[Execution Time][" + taskName + "]" + totalTime + "ms");
		return result;
	}

}
