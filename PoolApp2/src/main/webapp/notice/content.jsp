<%@page import="com.sds.PoolApp2.notice.Notice"%>
<%@page import="com.sds.PoolApp2.notice.NoticeDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! NoticeDAO noticeDAO = new NoticeDAO(); %>
<%! Notice notice = new Notice(); %>
<%
	//상세 내용을 채워 넣기 위해, 게시물 1건을 화면에 출력하자
	//select * from notice where notiec_idx? noitce_idx=?
	String notice_idx = request.getParameter("notice_idx");
	noticeDAO.select(Integer.parseInt(notice_idx));		
			
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@ include file="/inc/header_link.jsp"%>
<script type="text/javascript">
	function edit(){
		//동기 방식의 수정
		$("form").attr({
			action:"/notice/edit";, //서블릿에게 요청할 예정
			method:"post";
		});
		$("form").submit();
	}

	function regist(){
		//비동기 글쓰기 요청 
		let xhttp = new XMLHttpRequest();
		xhttp.open("POST", "/notice/regist"); //서블릿에게 글쓰기 요청함 
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		
		//비동기방식으로 요청을 시도하면, 요청을 담당했던 객체인 xhttp가 언제 요청에 대한 응답을 받아왔는지
		//알수가 없다..따라서 onreadystagechange 속성을 이용하여 그 시점을 감지하자 
		//요청 상태를 알려주는 속성인 readyState의 값이 바뀔때마다 아래의 익명함수가 , 콜백방식으로 호출됨
		xhttp.onreadystatechange = function(){
			if(this.readyState==4 && this.status == 200){ //요청을 제대로 처리하여 응답을 받을때
				console.log("서버에서 보내온 결과는 ", this.responseText); 
			}
		}
		
		let title=$("#title").val();
		let writer = $("#writer").val();
		let content=$("#content").val();
		
		xhttp.send("title="+title+"&writer="+writer+"&content="+content);
	}
	
	$(function(){
		$("#bt_regist").click(function(){
			regist();	
		});		
		
		$("#bt_list").click(function(){
			location.href="/notice/list.jsp";
		});
		$("#bt_eidt").click(function(){
			if(confirm("수정하시겠어요?")){
				edit();'
			}	
		});		
	});

</script>
</head>
<body>

	<div class="row">
		<div class="col-12">

			<div class="card card-info">
				<div class="card-header">
					<h3 class="card-title">Horizontal Form</h3>
				</div>

				<form class="form-horizontal">
					<div class="card-body">

						<div class="form-group row">
							<label for="inputEmail3" class="col-sm-2 col-form-label">제목</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="title" name="title"
									value="<%=notice.getTitle()%>">
							</div>
						</div>

						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-2 col-form-label">작성자</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="writer" name="writer"
									placeholder="작성자입력">
							</div>
						</div>

						<div class="form-group row">
							<label for="inputEmail3" class="col-sm-2 col-form-label">내용</label>
							<div class="col-sm-10">
								<textarea class="form-control" id="content" name="content" placeholder="제목 입력"> </textarea>
							</div>
						</div>
					</div>
				</form>
			</div>

			<div class="card-footer">
				<button type="button" class="btn btn-info" id="bt_regist">글쓰기</button>
				<button type="button" class="btn btn-default float-right"
					id="bt_list">목록</button>
				<button type="button" class="btn btn-info" id="bt_edit">수정</button>
				<button type="button" class="btn btn-default float-right"
					id="bt_del">삭제</button>
			</div>

			</form>
		</div>

	</div>
	</div>

</body>
</html>