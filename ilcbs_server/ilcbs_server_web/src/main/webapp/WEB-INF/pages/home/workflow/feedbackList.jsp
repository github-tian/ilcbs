<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script type="text/javascript">

	function markFeedback(va) {
		location.href="${ctx}/home/workflow/feedbackAction_markFeedback.action?id="+va;
	}
</script>
</head>
<body>
	<div id="feedback" class="feedback eXtremeTable">
		<div class="panel-title" align="center">
			<h1>意见反馈</h1>
		</div>

		<TABLE class="toptable grid" border="1" style="width: 100%">
			<TBODY>
				<tr height="40px">
					<td class="tableHeader" style="border-top: 0; border-bottom: 0;">提出人</td>
					<td class="tableHeader" style="border-top: 0; border-bottom: 0;">提出时间</td>
					<td class="tableHeader" style="border-top: 0; border-bottom: 0;">标题</td>
					<td class="tableHeader" style="border-top: 0; border-bottom: 0;">内容</td>
					<td class="tableHeader" style="border-top: 0; border-bottom: 0;">分类</td>
					<td class="tableHeader" style="border-top: 0; border-bottom: 0;">解决人</td>
					<td class="tableHeader" style="border-top: 0; border-bottom: 0;">状态</td>
				</tr>
				<c:forEach items="${feedbackList}" var="feed" varStatus="">
					<tr height="40px">

						<td class=category width="70px">${feed.inputBy}</td>
						<td class=category width="100px"><fmt:formatDate
								value="${feed.inputTime}" pattern="yyyy-MM-dd" /></td>
						<td class=category>${feed.title}</td>
						<td class=category width="500px">${feed.content}</td>

						<td class=category width="70px">${feed.classType}</td>
						<td class=category width="80px"><c:if
								test="${empty feed.answerBy}">
								<a href="#" style="color: #666" onclick="markFeedback('${feed.id}')">标记为已解决</a>
							</c:if> <c:if test="${!empty feed.answerBy}">
								${feed.answerBy}
							</c:if></td>
						<td class=category width="70px"><c:if
								test="${feed.state == '0'}">
								<font color="hotpink">未处理</font>
							</c:if> <c:if test="${feed.state==1}">
								<font color="lightgreen">已处理</font>
							</c:if></td>
					</tr>
				</c:forEach>

			</TBODY>
		</TABLE>
		<div style="margin-top: 20px" align="center">
			<form action="${ctx}/home/workflow/feedbackAction_feedbackContent.action" method="post">

				<div style="width: 500px; height: 200px">
					<span>请 提 出 您 的 建议标题</span> <input type="text" name="title" style="padding: 0 0 0 6px; margin: 0 0 8px 0;" placeholder="例如工作建议...">
					<br>

					<textarea style="height: 100px; padding: 6px;" name="content" placeholder="此处请填写建议..."></textarea>
				</div>
				<input type="submit" value="提交建议" style="margin: 0 0 20px 0;" >
			</form>

		</div>
	</div>
</body>
</html>