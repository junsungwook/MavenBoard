<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여기에 제목을 입력하십시오</title>
</head>
<body>
<center>
	<form action="passCheck">
		<input type="hidden" value="${board.seq }" name="seq" id="seq">
		<input type="hidden" value="${board.title }" name="title" id="title">
		<input type="hidden" value="${board.content }" name="content" id="content">
		비밀번호를 입력하세요<br>
		<input type="password" name="password" id="password"><br>
		<input type="submit" value="확인">
		<input type="button" value="뒤로" onclick="self.close()">
	</form>
</center>
</body>
</html>