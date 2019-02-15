<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<ul class="nav nav-sidebar">
	<li><a href="${pageContext.request.contextPath}/board">게시판 생성</a></li>
</ul>

<ul class="nav nav-sidebar">
	<c:forEach items="${boardList}" var="board">
		<c:if test="${board.use_exist == 'Y'}">
		    <li><a href="${pageContext.request.contextPath}/posting?board_num=${board.board_num}">${board.board_name}</a></li>
		</c:if>
	</c:forEach>
</ul>