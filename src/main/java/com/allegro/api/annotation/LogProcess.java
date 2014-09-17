package com.allegro.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.allegro.api.enumclass.ActionType;
import com.allegro.api.model.Model;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface LogProcess {
	public Class<? extends Model> className();
	public ActionType actionType();
	public String detailProcess() default "";
}
