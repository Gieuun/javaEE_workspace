<%@page import="com.sds.newsapp.news.News"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.sds.newsapp.news.NewsDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! 
	//이 영역은 현재 페이지인 list.jsp가 서블릿으로 변경되어 질때의 멤버영역이다
	//따라서 newsDAO를 has a 관계로 보유하려면, 멤버 영역인 선어부에 선언하자 
	NewsDAO newsDAO=new NewsDAO();
%>

<%
//이 영역은 현재 페이지인 list.jsp가 서블릿으로 변경되어 질때의 service() 메서드 영역이다
//따라서 요청 처리 로직을 작성할 수 있다
//평상시와는 틀리게, 오늘부터는 여기서 db연동 코드를 작성하지 않고 DAO에게 일을 시켜 그 결과를 가져오자
 List<News> list = newsDAO.selectAll();
%>

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
		<%for(int i=0;i<list.size();i++) {%>
		<%News news = list.get(i); %>
		<tr>
			<td><%%></td>
			<td>
			<a href="/news/content.jsp?news_idx=<%=news.getNews_idx()%>"><%=news.getTitle()%>
			</a>></td>
			<td><%= news.getWriter() %></td>
			<td><%= news.getRegdate() %></td>
			<td><%= news.getHit() %></td>
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
