<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<!-- Header -->
		<%-- <jsp:include page="/WEB-INF/views/includes/header.jsp" /> --%>
		<c:import url='/WEB-INF/views/includes/header.jsp'/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>				
					<c:set var='count' value='${fn:length(boardVo) }' />
					<c:forEach items="${boardVo }" var="boardVo" varStatus="status">
						<tr>
							<td hidden="hidden"><input name="no" value="${boardVo.no }"></td>
							<td>${count - status.index }</td>
							<td style="text-align:left; padding-left: ${20* boardVo.depth}px;">
								<a href="${pageContext.servletContext.contextPath }/board/view/${boardVo.no}">
									<c:if test="${boardVo.depth !=0 }">
										<img alt="" src="${pageContext.servletContext.contextPath }/assets/images/reply.png">
									</c:if>
								${boardVo.title }
								</a>
							</td>
							<td>${boardVo.name }</td>
							<td>${boardVo.count }</td>
							<td>${boardVo.regDate }</td>
							<td><a href="${pageContext.servletContext.contextPath }/board/delete/${boardVo.user_no}/${boardVo.no}" class="del">삭제</a></td>
						</tr>
					</c:forEach>
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>		
				
				
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath }/board/writeform" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<!-- 네비게이션 바  -->
		<%-- <jsp:include page="/WEB-INF/views/includes/navigation.jsp" /> --%>
		<c:import url='/WEB-INF/views/includes/navigation.jsp'>
			<c:param name="menu" value="board" />
		</c:import>

		<%-- <jsp:include page="/WEB-INF/views/includes/footer.jsp" /> --%>
		<c:import url='/WEB-INF/views/includes/footer.jsp' />
	</div>
</body>
</html>