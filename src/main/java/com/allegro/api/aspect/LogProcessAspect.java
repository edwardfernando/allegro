package com.allegro.api.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.allegro.api.annotation.LogProcess;
import com.allegro.api.model.Model;

@Aspect
@Component
public class LogProcessAspect {

	//@Before("execution(* com.allegro.api.service.UserService.save(..))")
	@Before("@annotation(logProcess) && args(object,..)")
	public void testLog(LogProcess logProcess, Model object) {
		System.out.println("Logging test is running");
		System.out.println("Context : "+object);
	}
}
