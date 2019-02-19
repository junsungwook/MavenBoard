<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여기에 제목을 입력하십시오</title>
</head>
<body>
<div class="container">
	<h2>게시판글등록</h2>
	<form action="write" method="post">
		<table class="table table-striped">
			<tr>
				<td class="td_left"><label for="writer">글쓴이</label></td>
				<td class="td_right"><input type="text" name="writer" id="writer"></td>
			</tr>
			<tr>
				<td class="td_left"><label for="password">비밀번호</label></td>
				<td class="td_right"><input name="password" type="password" id="password"></td>
			</tr>
			<tr>
				<td class="td_left"><label for="title">제 목</label></td>
				<td class="td_right"><input name="title" type="text" id="title"></td>
			</tr>
			<tr>
				<td class="td_left"><label for="content">내 용</label></td>
				<td><textarea id="content" name="content" cols="40" rows="15"></textarea></td>
			</tr>
		</table>
			<input type="submit" value="등록">&nbsp;&nbsp; <input	type="reset" value="다시쓰기">
	</form>
</div>
</body>
</html>