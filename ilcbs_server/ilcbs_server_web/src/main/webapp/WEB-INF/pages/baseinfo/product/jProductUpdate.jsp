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
<li id="save"><a href="#" onclick="formSubmit('productAction_update','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="/skin/default/images/icon/currency_yen.png"/>
   修改部门
  </div> 
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">描述：</td>
	            <td class="tableContent"><input type="text" name="description" value="${description}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">价格：</td>
	            <td class="tableContent"><input type="text" name="price" value="${price}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">尺寸长：</td>
	            <td class="tableContent"><input type="text" name="sizeLenght" value="${sizeLenght}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">尺寸宽：</td>
	            <td class="tableContent"><input type="text" name="sizeWidth" value="${sizeWidth}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">尺寸高：</td>
	            <td class="tableContent"><input type="text" name="sizeHeight" value="${sizeHeight}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">包装单位：</td>
	            <td class="tableContent">
	            <td class="tableContentAuto">
	            	<input type="radio" name="packingUnit" ${packingUnit=='PCS'?'checked':'' } value="PCS" class="input">只
	            	<input type="radio" name="packingUnit" ${packingUnit=='SETS'?'checked':'' } value="SETS" class="input">套
	            </td>
	            
	        </tr>	
	        <tr>
	            <td class="columnTitle">体积：</td>
	            <td class="tableContent"><input type="text" name="cbm" value="${cbm}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">大箱尺寸长：</td>
	            <td class="tableContent"><input type="text" name="mpsizeLenght" value="${mpsizeLenght}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">大箱尺寸宽：</td>
	            <td class="tableContent"><input type="text" name="mpsizeWidth" value="${mpsizeWidth}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">大箱尺寸高：</td>
	            <td class="tableContent"><input type="text" name="mpsizeHeight" value="${mpsizeHeight}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">备注：</td>
	            <td class="tableContent"><input type="text" name="remark" value="${remark}"/></td>
	        </tr>	
		</table>
	</div>
 
 
</form>
</body>
</html>

