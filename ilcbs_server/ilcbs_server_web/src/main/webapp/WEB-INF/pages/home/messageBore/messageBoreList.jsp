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
	<form action="${ctx}/home/workflow/messageBoardAction_mBoardSubmits.action" method="post">
	<h1>留言板</h1>
	<div class="eXtremeTable">
		<table id="ec_table" class="tableRegion" width="100%" border="1px">
			<thead>
				<tr height="50px">

					<td class="tableHeader">序号</td>
					<td class="tableHeader">用户名</td>
					<td class="tableHeader">内容</td>
					<td class="tableHeader">时间</td>
				</tr>
			</thead>
			<tbody class="tableBody">
				${links }
				<c:forEach items="${results}" var="o" varStatus="status">
					<tr height="50px">
						<td width="50px">${status.index+1}</td>
						<td width="100px">${o.username}</td>
						<td width="600px">${o.context}</td>
						<td width="100px"><fmt:formatDate value="${o.time}"
								pattern="yyyy-MM-dd" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<%-- <c:forEach items="${results}" var="o" varStatus="status">
				
						<td width="50px">${status.index+1}</td>
						<td width="100px">${o.username}</td>
						<td width="600px">${o.context}</td>
						<td width="100px"><fmt:formatDate value="${o.time}"
								pattern="yyyy-MM-dd" /></td>
				
				</c:forEach> --%>
		<hr width=100%>
		<div style="margin-top: 20px" align="center">
				<div style="width: 500px; height: 200px">
					<span style="font-size: 15px">我的留言：</span> <br> <br>

					<textarea style="height: 130px; padding: 6px;" name="context" placeholder="请填写反馈信息...
					"></textarea>
				</div>
				<input type="submit" style="margin: 0 0 20px 0;" value="点击提交">
		</div>
	</div>
	</form>
</body>
