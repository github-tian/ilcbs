<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
	<ul>
		<li id="view"><a href="#" onclick="formSubmit('invoiceAction_toview','_self');this.blur();">查看</a></li>
		<li id="view"><a href="#" onclick="formSubmit('invoiceAction_tocreate','_self');this.blur();">新增</a></li>
		<li id="update"><a href="#" onclick="formSubmit('invoiceAction_toupdate','_self');this.blur();">修改</a></li>
		<li id="new"><a href="#" onclick="formSubmit('invoiceAction_submit','_self');this.blur();">提交</a></li>
		<li id="delete"><a href="#" onclick="formSubmit('invoiceAction_delete','_self');this.blur();">删除</a></li>
	</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
    装箱列表
  </div> 
  
 <div style="color: red;font-size:16px;font-weight: bold;text-align: center ">
    <c:if test="${!empty perError}">
			${perError}
	</c:if>
</div>
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">编号</td>
		<td class="tableHeader">SC_NO</td>
		<td class="tableHeader">BL_NO</td>
		<td class="tableHeader">贸易条款</td>
		<td class="tableHeader">状态</td>
		
	</tr>
	</thead>
	<tbody class="tableBody" >
	${page.links}
	
	<c:forEach items="${page.results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
			<td><input type="checkbox" name="id" value="${o.id}"/></td>
			<td>${status.index+1}</td>
			<td>${o.id}</td>
			<td>${o.scNo}</td>
			<td>${o.blNo}</td>
			<td>${o.tradeTerms}</td>
			<td>
				<c:if test="${o.state==0 }">草稿</c:if>
				<c:if test="${o.state==1 }">已装箱</c:if>
				<c:if test="${o.state==2 }">已委托</c:if>
				<c:if test="${o.state==3 }">已通知</c:if>
				<c:if test="${o.state==4 }">已提交财务</c:if>
			</td>
			<td>
		  <%-- <div id="navMenubar">
		   <ul>
			<c:if test="${o.state==1 }"><li id="back"><a href="#" onclick="formSubmit('packingListAction_cancel','_self');this.blur();">委托</a></li></c:if>
			<c:if test="${o.state==2 }"><li id="back"><a href="#" onclick="formSubmit('packingListAction_cancel','_self');this.blur();">发票</a></li></c:if>
			<c:if test="${o.state==3 }"><li id="back"><a href="#" onclick="formSubmit('packingListAction_cancel','_self');this.blur();">财务</a></li></c:if>
			</ul>
		</div> --%>
		</td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

