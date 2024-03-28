<%@page import="com.sds.dataroom.board.Dataroom"%>
<%@page import="java.util.List"%>
<%@page import="com.sds.dataroom.board.DataroomDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! 
	DataroomDAO dataroomDAO=new DataroomDAO();
%>
<%
	List<Dataroom> list=dataroomDAO.selectAll();

	int totalRecord=list.size(); //총 레코드 수
	int pageSize=10; //한 페이지당 보여질 레코드 수 
	int totalPage= (int)Math.ceil((float)totalRecord/pageSize);
	int blockSize=10; //블럭당 보여질 페이지 수 
	int currentPage=1; //현재 유저가 보는 페이지 
	
	//유저가 페이지 번호를 선택할 경우, 1이 아닌 유저가 전송한 파라미터로 대체하자
	if(request.getParameter("currentPage") !=null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int firstPage=currentPage - (currentPage-1)%blockSize; //블럭당 for문의 시작 페이지 값 
	int lastPage=firstPage +(blockSize-1); //블럭당 for문의 끝 페이지 값 
	int curPos= (currentPage-1)*pageSize; //페이지당 List의 시작 index 
	int num = totalRecord - curPos; //페이지당 시작 게시물 번호
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="/inc/header_link.jsp" %>
<script type="text/javascript">
	$(function(){
			
		$("button").click(function(){
			location.href="/board/write.jsp";
		});
		
	});
</script>
</head>
<body>

	<div class="container">
		<h2>첨부파일 게시판</h2>
		<table class="table table-dark table-hover">
			<thead>
				<tr>
					<th>No</th>
					<th>제목</th>
					<th>작성자</th>
					<th>등록일</th>
					<th>조회수</th>
					<th>첨부파일</th>
				</tr>
			</thead>
			<tbody>
				<%for(int i=1;i<=pageSize;i++){ %>
				<%if(num<1)break;%>
				<%Dataroom dataroom = list.get(curPos++); %>
				<tr>
					<td><%=num-- %></td>
					<td><%=dataroom.getTitle() %></td>
					<td><%=dataroom.getWriter() %></td>
					<td><%=dataroom.getRegdate() %></td>
					<td><%=dataroom.getHit() %></td>
					<td><a href="/board/download.jsp?filename=<%=dataroom.getFilename() %>"><%=dataroom.getFilename() %></a></td>
				</tr>
				<%} %>
				<tr>
					<td colspan="6" align="center">
						<div class="row">
							<ul class="pagination">
							  <%if(firstPage-1>1){//이전 페이지가있을때만.. %>
							  	<li class="page-item"><a class="page-link" href="/board/list.jsp?currentPage=<%=firstPage-1%>">Previous</a></li>
							  <%}else{ %>
							  	<li class="page-item"><a class="page-link" href="javascript:alert('이전 페이지가 없습니다');">Previous</a></li>
							  <%} %>
							  
							  <%for(int i=firstPage;i<=lastPage;i++){%>
							  <%if(i>totalPage)break;%>
							  	<li class="page-item  <%if(currentPage==i){%>active<%}%>"><a class="page-link" href="/board/list.jsp?currentPage=<%=i%>"><%=i%></a></li>
							  <%} %>
							  <li class="page-item"><a class="page-link" href="/board/list.jsp?currentPage=<%=lastPage+1%>">Next</a></li>
							</ul>	
							
							<button class="btn-primary">글 등록</button>
						</div>
							
					</td>
				</tr>
			</tbody>
		</table>
	
	
	</div>

</body>
</html>