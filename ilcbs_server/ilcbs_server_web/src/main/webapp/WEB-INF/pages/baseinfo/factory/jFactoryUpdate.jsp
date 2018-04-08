<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form name="icform" method="post">
	<input type="hidden" name="id" value="${id}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('factoryAction_update','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="/skin/default/images/icon/currency_yen.png"/>
   厂家信息修改详情
  </div> 
  

 
    <div style="width: 80%">
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">产品类型：</td>
	            <td>
					<input type="radio" name="ctype" ${ctype=='货物'?'checked':'' } value="货物" checked class="input"/>货物
	            	<input type="radio" name="ctype" ${ctype=='附件'?'checked':'' } value="附件" class="input"/>附件
				</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">联系人：</td>
	            <td class="tableContent"><input type="text" name="contacts" value="${contacts}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">电话：</td>
	            <td class="tableContent"><input type="text" name="phone" value="${phone}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">手机号：</td>
	            <td class="tableContent"><input type="text" name="mobile" value="${mobile}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">传真：</td>
	            <td class="tableContent"><input type="text" name="fax" value="${fax}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">地址：</td>
	            <td class="tableContent"><input type="text" name="address" value="${address}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">验货员：</td>
	            <td class="tableContent"><input type="text" name="inspector" value="${inspector}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">说明：</td>
	            <td class="tableContent"><input type="text" name="remark" value="${remark}"/></td>
	        </tr>	
	        <tr style="width:300px">
	            <td class="columnTitle">合作状态：</td>
	            
	             <td align="left"><input type="radio" name="state" ${state==0?'checked':'' } value="0"/>未合作 </td> 
	          	 <td align="left"><input type="radio" name="state" ${state==1?'checked':'' } value="1"/>已合作 </td>
	           	<td align="left"><input type="radio" name="state" ${state==2?'checked':'' } value="2"/>已解除合作 </td>
	            
	        </tr>	
		</table>
	</div>
 
 
</form>
</body>
</html>

