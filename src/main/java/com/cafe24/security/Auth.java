package com.cafe24.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//어디다 붙여 쓰겠냐(파라미터냐 메서드냐 클래스냐)
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
	
	public enum Role {
		USER, ADMIN
	}

	public Role role() default Role.USER;

//	String value() default "USER";
//
//	int test() default 1;
}
