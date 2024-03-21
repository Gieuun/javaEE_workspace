<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
</style>
<title>뉴스 게시판</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		//버튼에 이벤트 연결
		$("button").click(function(){
			$(location).attr("href", "/news/write.jsp"); //location.href ="/new/write.jsp;"
		});
	});
</script>
</head>
<body>
	<table>
		<tr>
			<th>No</th>
			<th>뉴스 제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
		<%for(int i=1; i<=10; i++) {%>
		<tr>
			<td>Jill</td>
			<td>Smith</td>
			<td>50</td>
			<td>50</td>
			<td>50</td>
		</tr>
		<% }%>
		<tr>
			<td colspan="5">
				<button>글쓰기</button>
			</td>
		</tr>
	</table>

</body>
</html>
