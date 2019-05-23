<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
<div id="container">
		<!-- Header -->
		<c:import url='/WEB-INF/views/includes/header.jsp' />
		<div id="content">
			<div id="content">
				<div id="board">
					<p>
						잘못된 사용자 접근입니다.<br>
						<a href="${pageContext.servletContext.contextPath }/">메인화면으로 가기</a>
					</p>
				</div>
			</div>
		</div>
		<!-- 네비게이션 바  -->
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url='/WEB-INF/views/includes/footer.jsp' />
	</div>
</body>
</html>