<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<%@ include file="/module/header.jsp" %>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<%@ include file="/module/left.jsp"%>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">게시판 생성</h1>
				<form id="frmIns" action="${pageContext.request.contextPath}/board?type=1" method="post">
					<label>게시판이름</label>
					<label><input type="text" id="board_name_ins" name="board_name_ins"/></label>
					<label>
						<select id="use_exist_ins" name="use_exist_ins">
							<option value="Y">사용</option>
							<option value="N">미사용</option>
						</select>
					</label>
					<label><input id="btnIns" type="button" value="생성"/></label> <br>
					<label>===============================================</label> <br>
				</form>
				<c:forEach items="${boardList}" var="board">
					<form id="frmUpd" action="${pageContext.request.contextPath}/board?type=2" method="post">
						<label>게시판이름<input type="hidden" name="board_num_upd" value="${board.board_num}"/></label>
						<label><input type="text" class="board_name_upd" name="board_name_upd" value="${board.board_name}"/></label>
						<label>
							<select class="use_exist_upd" name="use_exist_upd">
								<option value="Y">사용</option>
								<option value="N">미사용</option>
							</select>
						</label>
						<label><input type="button" class="btnUpd" value="수정"/></label> <br>
					</form>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			//생성버튼 이벤트
			$("#btnIns").on("click", function(){
				if($("#board_name_ins").val().trim() == ""){
					alert("게시판 이름을 입력해주세요!");
					$("#board_name_ins").focus();
					return false;
				}else{
					$("#")
					$("#frmIns").submit();
				}
			});
			
			//수정버튼 이벤트
			$(".updBtn").on("click", function(){
				
				console.log($(this).parent("#frmUpd").submit());
				
				$(this).parent("#frmUpd").submit();
				
// 				if($(".upd_board_name").val().trim() == ""){
// 					alert("게시판 이름을 입력해주세요!");
// 					$(".upd_board_name").focus();
// 					return false;
// 				}else{
// 					$("#frmUpd").submit();
// 				}
			});
			
			//select box 이벤트
			$(".upd_use_exist").on("change", function(){
				console.log($(this).val());
			});
		});
	</script>
</body>
</html>