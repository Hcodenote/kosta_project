
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	//BoardDao2 dao = BoardDao2.getInstance();
	/* String n = request.getParameter("seq");
	int seq = 1;
	if (n!= null){
		seq = Integer.parseInt(n);
	} */
	//Board b = dao.contentsBoard(seq);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>글 세부보기</h3>
	<ul>
		<li> ${board.seq} </li>
		<li> ${board.title }</li>
		<li> ${board.writer }</li>
		<li> <a href="/MVC/download.jsp?filename=${board.fname }">${board.fname }</a></li>
		<li>${board.regdate }</li>
		<li>${board.contents }</li>
	</ul>
	<hr>
	<div>
		<form action="deleteAction.do?seq=${board.seq }" method="post">
			<input type="submit" value="삭제">
		</form>
		
		<form action="modifyForm.do" method="post">
			<input type="hidden" name = "seq" value="${board.seq }">
			<input type="submit" value="수정">
		</form>
	</div>
	<div>
		<h3>댓글 목록</h3>
		<table border="1">
			<tr>
				<td>댓글</td>
				<td>댓글제목</td>
				<td>댓글작성자</td>
				<td>댓글내용</td>
				<td>댓글날짜</td>
			</tr>
			<c:forEach var="reply" items="${replys }">
				<tr>
				<td>${reply.r_no }</td>
				<td>${reply.r_title }</td>
				<td>${reply.r_writer }</td>
				<td>${reply.r_contents }</td>
				<td>${reply.r_regdate }</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<form action="insertReplyAction.do" method="post">
		<input type="hidden" name="seq" value="${board.seq }">
		댓글제목 <input type = "text" name = "r_title"><br>
		댓글작성자 <input type = "text" name = "r_writer"><br>
		댓글내용 <input type = "text" name = "r_contents"><br>
		<input type="submit" value="댓글작성">
	</form>
</body>
</html>