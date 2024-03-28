<%@page import="com.sds.newsapp.map.StoreDAO"%>
<%@page import="com.sds.newsapp.map.Store"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! 
	//이 jsp 가 서블릿으로 변경될때, 멤버 영역
	StoreDAO storeDAO = new StoreDAO();
%>
<%
	//클라이언트가 비동기 요청을 시도한다는 것은, 현재 클라이언트가 보고 있는 페이지를 새로고침 하기를
	//원하지 않는다는 것이다. 따라서 서버에서는 완전한 html문서로 응답을 해서는 안됌!!
	//순수 데이터만을 보내주자 
	//현재 jsp의 경우, 글쓰기 역할을 수행해야 하므로, 등록 성공, 또는 실패 여부만을 보내주면 된다..
	
	request.setCharacterEncoding("utf-8");//파라미터가 깨지지 않도록...
	
	String lati=request.getParameter("lati");
	String longi=request.getParameter("longi");
	String name=request.getParameter("name");
	Store store = new Store(); //empty
	
	//비어있는 store 채우기 (setter 로 채우자)
	store.setName(name);
	store.setLati(Double.parseDouble(lati));
	store.setLongi(Double.parseDouble(longi));
	
	int result = storeDAO.insert(store);
	
	StringBuilder sb = new StringBuilder();
	
	sb.append("{");
	if(result>0){
		sb.append("\"result\":\"ok\"");
	}else{
		sb.append("\"result\":\"fail\"");
	}
	sb.append("}");
	
	out.print(sb.toString()); //결과를 json문자열로 보낸다...
	
	//out.print("lati="+lati+", longi="+longi+", name="+name);
%>