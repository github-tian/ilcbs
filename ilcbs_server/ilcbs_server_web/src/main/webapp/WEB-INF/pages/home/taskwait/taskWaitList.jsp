<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="feedback" class="feedback eXtremeTable">
		<div class="panel-title" align="center">
			<h1>待办任务</h1>
		</div>
		<div>
		<TABLE class="toptable grid" border="1" style="width: 100%">
			<TBODY>
				<tr height="40px">
					<td class="tableHeader" style="border-top: 0; border-bottom: 0;"><font style="font-size: 20px">待办事项</font></td>
					<td class="tableHeader" style="border-top: 0; border-bottom: 0;"><font style="font-size: 20px">个数</font></td>
					<td class="tableHeader" style="border-top: 0; border-bottom: 0;"><font style="font-size: 20px">说明</font></td>
					<td class="tableHeader" style="border-top: 0; border-bottom: 0;"></td>
				</tr>
				<tr height="40px">
					<td>购销合同</td>
					<td>${contractNum} 个</td>
					<td>
						检查货物是否正确<br>
						检查附件是否正确<br>
					</td>
					<td><a href="${ctx}/cargo/contractAction_list" style="color:red ; font-size:14px">点我</a></td>

				</tr>
				<tr height="40px">
					<td>合同管理</td>
					<td>${conExpNum} 单</td>
					<td>
						可否提交合同<br>
						是否成功报运
					</td>
					<td><a href="${ctx}/cargo/exportAction_contractList" style="color:red ; font-size:14px">点我</a></td>
	
				</tr>
				<tr height="40px">
					<td>出口报运</td>
					<td>${export} 单</td>
					<td>
						检查报运是否成功<br>
						提示装箱人员装箱
					</td>
					<td><a href="${ctx}/cargo/exportAction_list" style="color:red ; font-size:14px">点我</a></td>
				
				</tr>
				<tr height="40px">
					<td>装箱状态</td>
					<td>${shippingOrder} 单</td>
					<td>
						能否开始委托
					</td>
					<td><a href="${ctx}/cargo/packingListAction_list" style="color:red ; font-size:14px">点我</a></td>
				</tr>
			</TBODY>
		</TABLE>
		</div>
	</div>
</body>
</html>