<%@page import="com.sds.newsapp.news.News"%>
<%@page import="com.sds.newsapp.news.NewsDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! NewsDAO newsDAO = new NewsDAO(); %>
<%
	//클라이언트가 전송한 news_idx 파라미터를 받아서, 
	//DAO에게 한건 가져오게 시키자!!!
	String news_idx = request.getParameter("news_idx");
	out.print("클라이언트가 전송한 idx 값은" + news_idx);
	
	//ResultSet대신, NewsDTO로 게시물 1건 반환 받음
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
<!-- css -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">

<!-- js -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#content').summernote(); //WISIWYG 방식의 편집기

		$("input[type=button]").click(function() {
			$("form").attr({
				action : "/news/write",
				method : "post"
			});
			$("form").submit(); // 전송
		});
	});
</script>
</head>
<body>

	<h3>뉴스 상세보기</h3>

	<div class="container">
		<form>
			<input type="text" name="title" value="<%=news.getTitle()%>"> 
			<input type="text" name="writer" value="<%=news.getWriter()%>">
			<textarea id="content" name="content" placeholder="내용.." style="height: 400px"><%=news.getContent()%></textarea>

			<input type="button" value="수정">
			<input type="button" value="삭제">
			<input type="button" value="목록">
		</form>
	</div>

</body>
</html>