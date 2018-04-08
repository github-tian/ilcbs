<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title></title>
</head>
<body>
	<form  action="${ctx}/feedback/fankuiAction_back.action" method="post">
	<h1>系统反馈</h1>
		<div style="margin-top: 20px" align="center">
			<div style="width: 500px; height: 200px">
				<span style="font-size: 15px">我得反馈：</span>
				 <br><br>
				<textarea style="height: 130px;padding: 6px;" name="content" placeholder="请写下你的反馈..."></textarea>
			</div>
			<input type="submit" value="提交反馈">
		</div>
	</div>
	</form>
</body>
