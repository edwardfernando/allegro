package com.allegro.api.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LogProcessAspect {

	@Before("execution(* com.allegro.api.service.UserService.save(..))")
	public void testLog() {
		System.out.println("Logging TEst is running");
	}
}
