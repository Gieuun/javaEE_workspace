<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.sql.DataSource"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	/*
	톰켓이 지원하는 커넥션 풀에 접근해보기
	*/
	
	/*아래의 데이터소스가 커넥션풀을 구현한 구현체이다, 저 객체를 접근하려면 server.xml, context.xml
	외부 자원에 명시되어 있으므로, 자바코드 xml레서 자원을 검색해서 접근해야한다
	*/
	Context context = null; //xml 등 외부 자원을 검색하는 객체
	DataSource ds;	//커넥션풀 구현체
	
	//"java.comp/env 까진 정해진 이름으로 누락X
	
	//jndi 이름을 통해 구현체를 얻는다
	ds =(DataSource)context.lookup("java:comp/env/jndi/oracle");
	
	Connection con =ds.getConnection(); //풀로부터 커넥션 객체 1개 꺼내기
	//주의 여기서 접속이 발생한것이 아닌, 이미 접속되어진 객체를 얻어온 것이다
	//따라서 앞으로 개발자는 빌려완 Connection을 이용하여 원하는 쿼리문을 날리고
	//Connection을 끊지 않고 반납하자
	
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	String sql = "select * from notice";
	pstmt=con.prepareStatement(sql);
	rs=pstmt.executeQuery(); //select 문 수행
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% while(rs.next())%>
</body>
</html>