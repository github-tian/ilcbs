<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
     <script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
</head>

<body>
<form name="icform" method="post">
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('financeAction_insert','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   新增财务报运单
  </div> 
  

 
    <div>
    <table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">制单日期:</td>
	             <td class="tableContent"><input type="text" style="width:90px;" name="inputDate"
	            	 value=""
	             	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/></td>
	            </td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">制单人：</td>
	            <td class="tableContent"><input type="text" name="inputBy" value=""/></td>
	        </tr>		
	       <!--  <tr>
	            <td class="columnTitle">状态：</td>
	            <td class="tableContent"><input type="text" name="state" value=""/></td>
	        </tr>		 -->
		</table>
		
	</div>
	
	 <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
    装箱列表
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
	
	<c:forEach items="${invoices}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
			<td><input type="radio" name="id" value="${o.id}"/></td>
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

