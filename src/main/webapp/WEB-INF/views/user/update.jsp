<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%-- <%@page import="com.cafe24.mysite.vo.UserVo"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
<%-- <%
UserVo vo = (UserVo)request.getAttribute("userVo");
String gender = vo.getGender();
%> --%>
	<div id="container">
		<!-- Header -->
		<%-- <jsp:include page="/WEB-INF/views/includes/header.jsp" /> --%>
		<c:import url='/WEB-INF/views/includes/header.jsp' />
		<div id="content">
			<div id="user">
				<form id="join-form" name="joinForm" method="post" action='${pageContext.servletContext.contextPath }/user/update'>
					
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${authUser.name }" >

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="${authUser.email }" readonly="readonly" style="background-color: gray">
					<input type="button" value="id 중복체크">
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					<fieldset>
					<%-- <%if("female".equals(gender)){ %>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					<%} else{%>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female">
						<label>남</label> <input type="radio" name="gender" value="male"  checked="checked">
					<%} %> --%>
					<c:choose>
						<c:when test='${authUser.gender == "female" }'>
							<legend>성별</legend>
							<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
							<label>남</label> <input type="radio" name="gender" value="male">
						</c:when>
						<c:otherwise>
							<legend>성별</legend>
							<label>여</label> <input type="radio" name="gender" value="female">
							<label>남</label> <input type="radio" name="gender" value="male"  checked="checked">
						</c:otherwise>
					</c:choose>
					</fieldset>
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="변경하기">
					
				</form>
			</div>
		</div>
		<!-- 네비게이션 바  -->
		<%-- <jsp:include page="/WEB-INF/views/includes/navigation.jsp" /> --%>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
			<c:param name="menu" value="" />
		</c:import>

		<%-- <jsp:include page="/WEB-INF/views/includes/footer.jsp" /> --%>
		<c:import url='/WEB-INF/views/includes/footer.jsp' />
	</div>
</body>
</html>