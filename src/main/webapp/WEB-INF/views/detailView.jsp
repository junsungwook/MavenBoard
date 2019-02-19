<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여기에 제목을 입력하십시오</title>
</head>
<body>
	<div class="container">
		<form action="update" method="get">
			<table class="table table-striped">
				<tr>	
					<td>글번호</td><td>${board.seq }</td><td>작성자</td><td>${board.writer }</td>
					<input type="hidden" value="${board.seq }" name="seq" id="seq">
				</tr>
				<tr>
					<td>작성날짜</td><td>${board.regdate }</td><td>조회수</td><td>${board.hitcount }</td>
					
				</tr>
				<tr>
					<td>제목</td>
					<td colspan="3"><input type="text" class="form-control" value="${board.title }" name="title" id="title"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td colspan="3"><textarea class="form-control col-sm-5" rows="7" name="content" id="content">${board.content }</textarea></td>
				</tr>
				<tr>
					<input type="submit" value="글수정" class="btn btn-default">&nbsp;
					<input type="button" value="글삭제" class="btn btn-default" onclick="location='delete?seq=${board.seq }'">&nbsp;
					<input type="button" value="글목록" class="btn btn-default" onclick="location='list'">&nbsp;
					<input type="button" value="답글쓰기" class="btn btn-default" onclick="location='boardRe?BOARD_NUM=${board.seq }&BOARD_RE_REF=${board.groups }&BOARD_RE_SEQ=${board.steps }&BOARD_RE_LEV=${board.levels }'">
				</tr>
			</table>
		</form>
	</div>	
</body>
</html>