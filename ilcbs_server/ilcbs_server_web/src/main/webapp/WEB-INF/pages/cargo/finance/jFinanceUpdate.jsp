<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	 <script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
	
</head>

<body>
<form name="icform" method="post">
	<input type="hidden" name="id" value="${id}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('financeAction_update','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   修改部门
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
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
	        <!-- <tr>
	            <td class="columnTitle">状态：</td>
	            <td class="tableContentAuto"><input type="radio" name="state" value="0" class="input"/>草稿
	            						<input type="radio" name="state" value="1" class="input"/>已提交
	            </td>
	        </tr>		 -->
		</table>
		</table>
	</div>
 
 
</form>
</body>
</html>

