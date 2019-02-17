<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/dashboard.css" rel="stylesheet">

</head>
<body>
	<%@ include file="/header.jsp" %>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<%@ include file="/left.jsp"%>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">게시판</h1>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>게시글 번호</th>
							<th>제목</th>
							<th>작성자아이디</th>
							<th>작성일시</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${postingList}" var="posting">
							<tr class="postingTr" data-postingnum="${posting.posting_num}"> <!-- 해당 게시글 클릭했을때 정보얻기위해 필요 -->
								<td>${posting.posting_num}</td>
								<td>${posting.posting_title}</td>
								<td>${posting.posting_userid}</td>
								<td><fmt:formatDate value="${posting.posting_date}" pattern="yyyy-MM-dd"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<form id="frmIns" action="${pageContext.request.contextPath}/postingInsert" method="get">
					<input type="button" id="btnIns" value="새글등록"/>
				</form>
				
				<form id ="frmDetail" action="${pageContext.request.contextPath}/postingDetail" method="get">
					<input type="hidden" id="posting_num" name="posting_num"/>
				</form>
				
			</div>
		</div>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			//해당 게시글 클릭시 이벤트
			$(".postingTr").on("click", function(){
				var posting_num = $(this).data("postingnum");
				
				$("#posting_num").val(posting_num);
				$("#frmDetail").submit();
			});
			
			$("#btnIns").on("click", function(){
				
			});
		});
	</script>
</body>
</html>