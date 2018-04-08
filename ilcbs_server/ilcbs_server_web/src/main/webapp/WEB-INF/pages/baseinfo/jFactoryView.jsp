<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<s:debug></s:debug>
<form name="icform" method="post">
      <input type="hidden" name="id" value="${id}"/>
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   浏览厂家信息
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	         <tr>
	            <td class="columnTitle">厂家全称：</td>
	            <td class="tableContent">${fullName }</td>
	            <td class="columnTitle">厂家简称：</td>
	            <td class="tableContentAuto">${factoryName }</td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">联系人：</td>
	            <td class="tableContent">${contacts }</td>
	            <td class="columnTitle">电话：</td>
	            <td class="tableContent">${phone }</td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">手机：</td>
	            <td class="tableContent">
					<input type="hidden" name="haha"/>
					<c:if test="${not empty o.mobile}">${o.mobile }</c:if>
					<c:if test="${empty o.mobile}"><font style="color:#999;">待补全</font></c:if>
	            </td>
	            <td class="columnTitle">传真：</td>
	            <td class="tableContent">
	            	<input type="hidden" name="haha"/>
					<c:if test="${not empty o.fax}">${o.fax }</c:if>
					<c:if test="${empty o.fax}"><font style="color:#999;">待补全</font></c:if>
	            </td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">地址：</td>
	            <td class="tableContent">${address }</td>
	            <td class="columnTitle">验货员：</td>
	            <td class="tableContent">${inspector }</td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">说明：</td>
	            <td class="tableContent">
					<input type="hidden" name="haha"/>
					<c:if test="${not empty o.remark}">${o.remark }</c:if>
					<c:if test="${empty o.remark}"><font style="color:#999;">待补全</font></c:if>
				</td>
	        </tr>		
		</table>
	</div>
 </form>
 
 <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
    货物列表
  </div> 


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">厂家</td>
		<td class="tableHeader">货号</td>
		<td class="tableHeader">装率</td>
		<td class="tableHeader">箱数</td>
		<td class="tableHeader">包装单位</td>
		<td class="tableHeader">数量</td>
		<td class="tableHeader">单价</td>
		<td class="tableHeader">总金额</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
		<c:forEach items="${contractProducts}" var="cp" varStatus="status">
			<tr bgcolor="#c3f3c3" height="30" class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
				<td>${status.index+1}</td>
				<td>${cp.factoryName}</td>
				<td>${cp.productNo}</td>
				<td>${cp.loadingRate}</td>
				<td>${cp.boxNum}</td>
				<td>${cp.packingUnit}</td>
				<td>${cp.cnumber}</td>
				<td>${cp.price}</td>
				<td>${cp.amount}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div> 
</body>
</html>