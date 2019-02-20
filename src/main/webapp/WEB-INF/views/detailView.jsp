<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여기에 제목을 입력하십시오</title>
<script> 
function info_chk2(frm) { 
 frm.action='delete'; 
 frm.submit(); 
 return true; 
} 
$(document).ready(function(){
	$.ajax({
		url:"C_List",
		type:"get",
		data:{"seq":"${board.seq }"},
		success:function(data){
			if(data!=null){
				data = $.parseJSON(data);
				var htmlStr="";
				htmlStr +="<table class='table table-striped table-dark'>";
				for(var i=0; i<data.length;i++){
					htmlStr +="<tr>";
					htmlStr +="<td>"+data[i].cnum+"</td>";
					htmlStr +="<td>"+data[i].writer+"</td>";
					htmlStr +="<td>"+data[i].regdate+"</td>";
					htmlStr +="<td>"+data[i].msg+"</td>";
					htmlStr +="</tr>";
				}
				htmlStr +="</table>";
				$("#result").html(htmlStr);	
			}
		},
		error:function(e){
			alert("error : "+ e);
		}
	});
	$("#commentBtn").click(function(){
		$.ajax({
			url:"C_Insert",
			type:"post",
			data:{"msg":$("#msg").val(),"seq":"${board.seq }","writer":$("#cwriter").val()},
			success:function(data){
				data = $.parseJSON(data);
				var htmlStr="";
				htmlStr +="<table class='table table-striped table-dark'>";
				for(var i=0; i<data.length;i++){
					htmlStr +="<tr>";
					htmlStr +="<td>"+data[i].cnum+"</td>";
					htmlStr +="<td>"+data[i].writer+"</td>";
					htmlStr +="<td>"+data[i].regdate+"</td>";
					htmlStr +="<td>"+data[i].msg+"</td>";
					htmlStr +="</tr>";
				}
				htmlStr +="</table>";
				$("#result").html(htmlStr);	
				$("#msg").val("");
			},
			error:function(e){
				alert("inserterror : "+ e);
			}
		});
	});
});
</script> 
</head>
<body>
${board.password }
	<div class="container">
		<form action="update" method="post">
			<table class="table table-striped">
				<tr>	
					<td>글번호</td><td>${board.seq }</td><td>작성자</td><td>${board.writer }</td>
					<input type="hidden" value="${board.seq }" name="seq" id="seq">
					<input type="hidden" value="${board.writer }" name="writer" id="writer">
					<input type="hidden" value="${board.password }" name="password" id="password">
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
					<input type="button" value="글삭제" class="btn btn-default" onclick="return info_chk2(this.form);">&nbsp;
					<input type="button" value="글목록" class="btn btn-default" onclick="location='list'">&nbsp;
					<input type="button" value="답글쓰기" class="btn btn-default" onclick="location='reply?groups=${board.groups }&levels=${board.levels }&steps=${board.steps }'">
				</tr>
			</table>
			비번쳐라<input type="password" name="password2" id="password2">
		</form>
		<div id="result"></div>
		<div align="right">
			아이디 입력 <input type="text" value="" name="writer" id="cwriter">
			<textarea rows="5" cols="50" id="msg" class="form-control"></textarea>
			<input type="button" class="btn btn-default" value="댓글쓰기" id="commentBtn" class="btn btn-default">
		</div>
	</div>	
</body>
</html>