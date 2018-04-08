<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.js"></script>
	<script>  
			function isOnlyChecked(){
		    	 var checkBoxArray = document.getElementsByName('id');
					var count=0;
					for(var index=0; index<checkBoxArray.length; index++) {
						if (checkBoxArray[index].checked) {
							count++;
						}	
					}
				if(count==1)
					return true;
				else
					return false;
		     }
	       //实现查看
	       function toview(){
	           if(isOnlyChecked()){  
	                   formSubmit('factoryAction_toview','_self');  
	           }else{  
	           	   alert("请先选择一项并且只能选择一项，再进行操作！");  
	           }
	       }
	</script>
</head>

<body>
<s:debug></s:debug>
<form name="icform" method="post">
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="view"><a href="#" onclick="javascript:toview()">查看</a></li>
<li id="update"><a href="#" onclick="formSubmit('factoryAction_toupdate','_self');this.blur();">修改</a></li>
<li id="delete"><a href="#" onclick="formSubmit('factoryAction_delete','_self');this.blur();">删除</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
    出口报运列表
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">工厂全名</td>
		<td class="tableHeader">工厂简称</td>
		<td class="tableHeader">联系人</td>
		<td class="tableHeader">联系电话</td>
		<td class="tableHeader">手机号码</td>
		<td class="tableHeader">传真</td>
		<td class="tableHeader">厂家地址</td>
		<td class="tableHeader">检察员</td>
		<td class="tableHeader">备注</td>
		<td class="tableHeader">状态</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	${links}
	<c:forEach items="${results}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
	<td><input type="checkbox" name="id" value="${o.id}"/></td>
	
		<td>${status.index+1}</td>
		<!-- 全名 -->
		<td><a href="factoryAction_toview?id=${o.id}">${o.fullName}</a></td>
		<!-- 简称 -->
		<td>${o.factoryName}</td>
		<!-- 联系人 -->
		<td>${o.contacts}</td>
		<!-- 电话 -->
		<td>
				<input type="hidden" name="haha"/>
				<c:if test="${not empty o.phone}">${o.phone }</c:if>
				<c:if test="${empty o.phone}"><font style="color:#999;">待补全</font></c:if>
		</td>
		<!-- 手机 -->
		<td>
			<input type="hidden" name="haha"/>
			<c:if test="${not empty o.mobile}">${o.mobile }</c:if>
			<c:if test="${empty o.mobile}"><font style="color:#999;">待补全</font></c:if>
		</td>
		<!-- 传真 -->
		<td>
			<input type="hidden" name="haha"/>
			<c:if test="${not empty o.fax}">${o.fax }</c:if>
			<c:if test="${empty o.fax}"><font style="color:#999;">待补全</font></c:if>
		</td>
		<!-- 地址 -->
		<td>
				<input type="hidden" name="haha"/>
				<c:if test="${not empty o.address}">${o.address }</c:if>
				<c:if test="${empty o.address}"><font style="color:#999;">待补全</font></c:if>
		</td>
		<!-- 检察员 -->
		<td>
				<input type="hidden" name="haha"/>
				<c:if test="${not empty o.inspector}">${o.inspector }</c:if>
				<c:if test="${empty o.inspector}"><font style="color:#999;">待补全</font></c:if>
		</td>
		<!-- 备注 -->
		<td>
				<input type="hidden" name="haha"/>
				<c:if test="${not empty o.remark}">${o.remark }</c:if>
				<c:if test="${empty o.remark}"><font style="color:#999;">待补全</font></c:if>
		</td>
		<!-- 状态 -->
		<td>
			<input type="hidden" name="haha" value="${o.state }"/>
			<c:if test="${o.state==0}">草稿</c:if>
			<c:if test="${o.state==1}"><font color="green">开工</font></c:if>
			<c:if test="${o.state==2}"><font color="hotpink">已装箱</font></c:if>
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

