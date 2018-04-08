<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
</head>

<body>
<div style="width: 480px; height: auto; margin: 18px auto;">
<form name="icform" method="post">
	<div class="textbox" id="centerTextbox">
		<div class="textbox-header">
			<div class="textbox-inner-header">
				<div class="textbox-title" style="width: 360px;">修改个人信息</div> 
			</div>
		</div>
		<div style="width: 480px">
			<table class="commonTable" cellspacing="1" style="width: 360px;">
    	<tr>
        <%--   <td class="columnTitle">所在部门：</td>
			<td class="tableContent">
          	<s:select name="dept.id" list="deptList"
          		listKey="id" listValue="deptName"
          		headerKey="" headerValue="--请选择--"
          	></s:select>
          </td> --%>
          
          <input type="hidden" name="id" value="${id }"/>
      </tr> 
     	<tr>
          <td class="columnTitle">姓名：</td>
          <td class="tableContent"><input type="text" name="userinfo.name"  readonly="readonly" value="${userinfo.name }"/></td>
         	<td style="width: 120px"><font style="color: red;">(不可修改)</font></td>
      <tr>
     	<tr >
          <td class="columnTitle">登录名：</td>
          <td class="tableContent" ><input type="text" name="userName" value="${userName }"></input></td>
      </tr>
	<td class="columnTitle">性别：</td>
          <td class="tableContentAuto">
          	<input type="radio" name="userinfo.gender" value="1" class="input" <c:if test="${userinfo.gender==1 }">checked='cheked'</c:if> />男
          	<input type="radio" name="userinfo.gender" value="0" class="input" <c:if test="${userinfo.gender==0 }">checked='cheked'</c:if> />女
          </td>
      </tr>	
     	<tr>
          <td class="columnTitle">岗位：</td>
          <td class="tableContent"><input type="text" readonly="true"  name="userinfo.station" value="${userinfo.station}" /></span></td>
          <td style="width: 120px"><font style="color: red;">(不可修改)</font></td>
      </tr>	
     	<tr>
          <td class="columnTitle">电话：</td>
          <td class="tableContent"><input type="text" name="userinfo.telephone" value="${userinfo.telephone}" ></input></td>
      </tr>	
     	<tr>
     	    <td class="columnTitle">邮箱：</td>
          <td class="tableContent"><input type="text" name="userinfo.email" value="${userinfo.email}" ></input></td>
      </tr>	
     	<tr>
          <td class="columnTitle">出生年月：</td>
          <td class="tableContent">
         
		<input type="text" style="width:85px;" name="userinfo.birthday"
          	 value=' <fmt:formatDate value="${userinfo.birthday}" pattern="yyyy-MM-dd" />'
           	onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});" ></input>
	</td>
      </tr>	
			</table>
	</div>
	<div id="menubar">
		<div id="middleMenubar">
			<div id="innerMenubar">
	  			<div id="navMenubar">
					<ul>
						<li id="save"><a href="#" onclick="formSubmit('userInfoAction_update','_self');this.blur();">保存</a></li>
						<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
					</ul>
	  			</div>
			</div>
		</div>
	</div>
</form>
</div>
</body>
</html>

