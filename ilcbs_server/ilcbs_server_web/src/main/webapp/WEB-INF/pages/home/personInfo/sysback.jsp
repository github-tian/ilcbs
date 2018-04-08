<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<style type="text/css">
			
				div {
				width: 100%;
				height:500px;
				border: 1px solid #e1e1e1;
				margin: 0 auto;
				background: #fff;
			} 
			
			
			h1 {
				margin: 0 auto;
				color: #7c7c7c;
			}
			
		</style>
</head>
<body>
<form method="post">
			<div background-image:/src/main/webapp/img/1.jpg>
				<h1>反馈内容:</h1>
				<textarea placehoder="请给出您的建议---谢谢合作!" style="width:900px ; height:400px" name="textarea"></textarea>
				<br>
				<button><a href="#" onclick="formSubmit('sysBackAction_sendMail','_self');this.blur();">发送</a></button>
				 
				<button><a href="#" onclick="history.go(-1);">返回</a></button>
				
			</div>
</form>



</body>


</html>