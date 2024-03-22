<%@page import="com.sds.newsapp.news.News"%>
<%@page import="com.sds.newsapp.news.NewsDAO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!NewsDAO newsDAO = new NewsDAO();%>
<%
//클라이언트가 전송한 news_idx 파라미터를 받아서, 
//DAO에게 한건 가져오게 시키자!!!
String news_idx = request.getParameter("news_idx"); // null
out.print("클라이언트가 전송한 idx 값은" + news_idx);

//ResultSet 대신 , News DTO로 게시물 1건 반환 받음
News news = newsDAO.select(Integer.parseInt(news_idx));
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

input[type=text], select, textarea {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

input[type=button] {
	background-color: #04AA6D;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=button]:hover {
	background-color: #45a049;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}
</style>
<%@ include file="../inc/head.jsp"%>

<!-- include libraries(jQuery, bootstrap) -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<script type="text/javascript">
	let obj;
	//이 함수는 최최에 onLoad 시에도 호출되어야 하지만, 글 등록시에도 비동기방식으로 목록을 가져와 
	//갱신해야 하기 때문에 ajax 로 처리한다..(즉 새로고침 없이 목록이 갱신되어야 하므로...)
	function getCommentsList(){
		let xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange=function(){
			if(this.readyState==4 && this.status==200){
				//댓글 목록을 처리~~~~
				console.log(this.responseText);
				//json은 문자열이며 객체가 아니다. 따라서 분석할 수 없다. ex) 배열의 길이, 인덱스의 값 등..
				//해결책) 넘어온 문자열을 분석하여 객체화 시키면된다, js의 JSON 내장 객체가 지원한다
				obj = JSON.parse(this.responseText);
				
				//분석한 이후부터는, 서버로부터 넘겨 받은 문자열은 지금부터 객체로 취급한다
				console.log("댓글 수 는?"+obj.commentsList.length);
				
				let tag="<table width=\"100%\" border=\"1px\">";

				tag+="<tr>";
				tag+="<th>댓글 메시지</th>";
				tag+="<th>작성자</th>";
				tag+="<th>등록일</th>";
				tag+="</tr>";
				for(let i=0;i<obj.commentsList.length;i++){
					let json = obj.commentsList[i];//배열로 부터 json 객체 하나를 꺼낸다
					tag+="<tr>";
					tag+="<td>"+json.msg+"</td>";
					tag+="<td>"+json.cwriter+"</td>";
					tag+="<td>"+json.cregdate+"</td>";
					tag+="</tr>";
				}
				tag+="</table>";
				
				$("#comments_container").html(tag);
			}
		}
		
		xhttp.open("GET", "/comments/list?news_idx=<%=news_idx%>");
		xhttp.send();//백그라운드 요청 시작
	}

	$(function(){
		$('#content').summernote();
		
		$("#bt_del").click(function(){
			if(confirm("삭제하시겠어요?")){
				//delete news where news_idx=3
				location.href="/news/delete?news_idx=<%=news_idx%>";
			}
		});

		$("#bt_list").click(function() {
			location.href = "/news/list.jsp";
		});

		$("#bt_edit").click(function() { //수정 버튼 누르면..
			if (confirm("수정하시겠어요?")) {
				$("#form1").attr({
					action : "/news/edit",
					method : "post"
				});
				$("#form1").submit();//전송
			}
		});

		//댓글 등록 요청 
		$("#bt_reply").click(
				function() {
					//비동기 통신으로 전송...
					let xhttp = new XMLHttpRequest(); //비동기 백그라운드 통신 객체 생성

					//서버의 응답이 성공적으로 들어오면...
					xhttp.onreadystatechange = function() {
						if (this.readyState == 4 && this.status == 200) { //요청이 도달하고, 서버에서 요청을 정상적으로 처리햇다면...
							alert(this.responseText);
						}
					}

					//통신 전 헤더 구성하기 
					xhttp.open("POST", "/comments/regist");

					xhttp.setRequestHeader("Content-type",
							"application/x-www-form-urlencoded");

					let msg = $("#msg").val(); //메시지 값
					let cwriter = $("#cwriter").val(); //작성자 값
					let news_idx =<%=news_idx%>;

					xhttp.send("msg=" + msg + "&cwriter=" + cwriter
							+ "&news_idx=" + news_idx);//파라미터는 이따가 보내자
				});

		//무조건, 이 기사에 딸려있는 댓글의 목록 가져오기 
		getCommentsList();
	});
</script>
</head>
<body>

	<h3>뉴스 상세보기</h3>

	<div class="container">
		<form id="form1">
			<input type="hidden" name="news_idx" value="<%=news.getNews_idx()%>">

			<input type="text" name="title" value="<%=news.getTitle()%>">
			<input type="text" name="writer" value="<%=news.getWriter()%>">
			<textarea id="content" name="content" placeholder="내용.."
				style="height: 400px"><%=news.getContent()%></textarea>

			<input type="button" value="수정" id="bt_edit"> <input
				type="button" value="삭제" id="bt_del"> <input type="button"
				value="목록" id="bt_list">
		</form>
	</div>

	<!--댓글 등록 폼  -->
	<div class="container">
		<form id="form2">
			<input type="text" id="msg" style="width: 70%"
				placeholder="댓글을 입력하세요..."> <input type="text" id="cwriter"
				style="width: 15%" placeholder="작성자"> <input type="button"
				value="등록" id="bt_reply" style="width: 10%">
		</form>
	</div>

	<!--댓글 목록 -->
	<div class="container" id="comments_container"></div>

</body>
</html>