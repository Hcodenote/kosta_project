<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h2>게시물 수정</h2>
		<form action="modifyBoard.do" method="post">
			<input type="hidden" name = "seq" value="${seq }">
			작성자 : <input type="text" name="writer" value=""><br>
			제목 : <input type="text" name="title" value=""><br>
			내용 <br>
			<textarea rows="6" cols="70" name="contents" value=""></textarea>
			<br>
			<input type="submit" value="등록">
		</form>
	</div>
</body>
</html>