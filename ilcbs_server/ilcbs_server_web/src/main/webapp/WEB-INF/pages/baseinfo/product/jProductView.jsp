<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
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
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="/skin/default/images/icon/currency_yen.png"/>
   浏览部门
  </div>
  

 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">货号：</td>
	            <td class="tableContent">${productNo}</td>
	       
	            <td class="columnTitle">图片：</td>
	            <td class="tableContent">
	            	<div>
	            	<img src="${ctx }/${productImage}" width="400px" height="250px">
	            	</div>
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">描述：</td>
	            <td class="tableContent">${description}</td>
	        
	            <td class="columnTitle">厂家名称：</td>
	            <td class="tableContent">${factoryName}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">价格：</td>
	            <td class="tableContent">${price}</td>
	       
	            <td class="columnTitle">尺寸长：</td>
	            <td class="tableContent">${sizeLenght}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">尺寸宽：</td>
	            <td class="tableContent">${sizeWidth}</td>
	        
	            <td class="columnTitle">尺寸高：</td>
	            <td class="tableContent">${sizeHeight}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">颜色：</td>
	            <td class="tableContent">${color}</td>
	       
	            <td class="columnTitle">包装：</td>
	            <td class="tableContent">${packing}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">包装单位：</td>
	            <td class="tableContent">${packingUnit}</td>
	       
	            <td class="columnTitle">体积：</td>
	            <td class="tableContent">${cbm}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">大箱尺寸长：</td>
	            <td class="tableContent">${mpsizeLenght}</td>
	        
	            <td class="columnTitle">大箱尺寸宽：</td>
	            <td class="tableContent">${mpsizeWidth}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">大箱尺寸高：</td>
	            <td class="tableContent">${mpsizeHeight}</td>
	        
	            <td class="columnTitle">备注：</td>
	            <td class="tableContent">${remark}</td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">录入时间：</td>
	            <td class="tableContent">${inputTime}</td>
	        </tr>	
		</table>
	</div>
 
 
</form>
</body>
</html>

