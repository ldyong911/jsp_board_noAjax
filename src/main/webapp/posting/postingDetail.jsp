<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<h1 class="page-header">게시글 상세조회</h1>
				
				<form id="frmUpd" action="${pageContext.request.contextPath}/postingUpdate"
					  class="form-horizontal" method="get">
					<input type="hidden" name="board_num" value="${board_num}"/>
					<input type="hidden" id="type" name="type" value="1"/>
					<div class="form-group">
						<label for="posting_title" class="col-sm-2 control-label">제목</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="posting_title" name="posting_title"
								placeholder="제목" value="${postingVO.posting_title}" readonly/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="posting_content" class="col-sm-2 control-label">내용</label>
						<div class="col-sm-10">
							${postingVO.posting_content}
						</div>
					</div>
					
					<div class="form-group">
						<label for="attachfile" class="col-sm-2 control-label">첨부파일</label>
						<div class="col-sm-5">
							<input id="filename" class="form-control" readonly/>
						</div>
						
						<input id="btnUpd" type="button" value="수정"/>
						<input id="btnDel" type="button" value="삭제"/>
						<!-- 답글버튼 클릭시 해당 게시글번호를 넘겨줘야함 -->
						<input type="hidden" name="parentposting_num" value="${postingVO.posting_num}"/>
						<input id="btnIns" type="button" value="답글"/>
					</div>
					
					<!-- 댓글 목록 출력해야함 -->
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input id="btnUpdReply" type="button" value="댓글수정"/>
						</div>
					</div>
					<!--  -->
					
					<div class="form-group">
						<label for="reply_content" class="col-sm-2 control-label">댓글</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="reply_content" name="reply_content"/>
						</div>
						<input id="btnInsReply" type="button" value="댓글저장"/>
					</div>
					
				</form>
				
			</div>
		</div>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	
	<script src="/SE2/js/HuskyEZCreator.js"></script>
	<script type="text/javascript">
	
	$(document).ready(function() {
		//첨부파일 이벤트
		$("#attach").on("change", function(){
			var attach = document.getElementById("attach");
			var files = attach.files;
			var filename = "";
			
			for(var i=0; i<files.length; i++){
				console.log(files[i].name);
				filename += files[i].name;
			}
			$("#filename").val(filename);
		});
	
// 		$("#frm").attr("action", "/user"); //속성바꿀때 사용
		//수정버튼 클릭이벤트
		$("#btnUpd").on("click", function(){
			
		});
		
		//삭제버튼 클릭이벤트
		$("#btnUpd").on("click", function(){
			
		});
		
		//답글버튼 클릭이벤트
		$("#btnIns").on("click", function(){
			$("#frmUpd").attr("action", "${pageContext.request.contextPath}/postingInsert"); //속성바꿀때 사용
		});
	});
	</script>
</body>
</html>