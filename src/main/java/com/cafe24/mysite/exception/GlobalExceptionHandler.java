package com.cafe24.mysite.exception;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cafe24.mysite.dto.JSONResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);

	// Exception을 처리하는 메서드다.
	// 스프링 MVC가 일어나는곳이 아님 =>ViewResolve 타지않음
	@ExceptionHandler(Exception.class)
	public void handleUserDaoException(HttpServletRequest request, HttpServletResponse response, Exception e)
			throws Exception {
//		1. 로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		
		LOGGER.error(errors.toString());
		
		System.out.println(errors.toString());

		String accept = request.getHeader("accept");

//		JSON포멧으로 바꿔 주는 것 (에러메세지를)
//		.* <= 모든 문자 포함 
		if (accept.matches(".*application/json.*")) {
			// JSON 응답
			response.setStatus(HttpServletResponse.SC_OK);

			JSONResult jsonResult = JSONResult.fail(errors.toString());

			//
			String result = new ObjectMapper().writeValueAsString(jsonResult);
			System.out.println(result);

			OutputStream os = response.getOutputStream();

			os.write(result.getBytes("UTF-8"));
			os.flush();
			os.close();

		} else {
//		2. 안내페이지 가기 + 정상종료(response).
			request.setAttribute("uri", request.getRequestURI());
			request.setAttribute("exception", errors.toString());
			request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
		}
	}
}
