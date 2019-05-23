package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mysite.vo.UserVo;

public class AuthIntercetpor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 걸러야 하는 것 순서대로

		// 1. handler 종류 확인
		if (handler instanceof HandlerMethod == false) {
			// handler method가 아닌것들은 무조건 보내주자
			return true;
		}
		// 2. 컨트롤러의 메서드인것이 확인 되었다
		// casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3 Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		// 4. Method에 @Auth없으면
		// Class(TYPE)에 @Auth를 받아오기
//		if (auth == null) {
//			return true;
//		}

		// 5. Method 와 class에도 @Auth가 없다
		if (auth == null) {
			return true;
		}

		// 6. @Auth가 Class나 Method에 붙어 있기 때문에
		// 인증여부 체크
		HttpSession session = request.getSession();
		if (session == null) {// 인증이 안 되어있음
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		// 7.Role가져오기
//		Auth.Role role = auth.role();

		// 8.Role이 Auth.Role.USER 라면,
		// 인증된 모든 사용자는 접근 가능
//		if (role == Auth.Role.USER) {
//			return true;
//		}

		// 9.Admin Role 권한 체크
		//
//		authUser.getRole().equals("ADMIN")
		//
		return true;
	}

}
