package com.cafe24.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	// Exception을 처리하는 메서드다.
	// 스프링 MVC가 일어나는곳이 아님 =>ViewResolve 타지않음
	@ExceptionHandler(Exception.class)
	public void handleUserDaoException(HttpServletRequest request, HttpServletResponse response, Exception e)
			throws Exception {
//		1. 로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		System.out.println(errors.toString());

//		2. 안내페이지 가기 + 정상종료(response).
		request.setAttribute("uri", request.getRequestURI());
		request.setAttribute("exception", errors.toString());
		request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
	}
}
