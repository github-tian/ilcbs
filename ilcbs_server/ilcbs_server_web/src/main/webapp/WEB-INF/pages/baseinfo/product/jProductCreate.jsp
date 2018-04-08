<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript">
		//动态设置生产厂家的名称 
		function setFactoryName(val){
			document.getElementById("factoryName").value = val;
		}
	</script>
</head>

<body>
<form name="icform" method="post" enctype="multipart/form-data" >

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('productAction_insert','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="/skin/default/images/icon/currency_yen.png"/>
   新增产品
  </div> 
  

 
    <div style="width: 80%">
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">货号：</td>
	            <td class="tableContent"><input type="text" name="productNo" value=""/></td>
	       
	            <td class="columnTitle">产品图片：</td>
	            <td class="tableContent">
	            <input type="file"  name="productImageFile"  value=""/>
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">描述：</td>
	            <td class="tableContent"><input type="text" name="description" value=""/></td>
	       
	        	<td class="columnTitle">厂家：</td>
	        	<td class="tableContent">
	            	 
	            	<input type="text" id="factoryName"  style="width:264px;margin-right: 7px" name="factoryName" readonly="readonly" value="${factoryName }"><font style="color: red">不可修改</font></input>
	            	
	            </td>
	         </tr>
	        <tr>
	            <td class="columnTitle">市场价：</td>
	            <td class="tableContent"><input type="text" name="price" value=""/></td>
	      
	            <td class="columnTitle">尺寸长：</td>
	            <td class="tableContent"><input type="text" name="sizeLenght" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">尺寸宽：</td>
	            <td class="tableContent"><input type="text" name="sizeWidth" value=""/></td>
	      
	            <td class="columnTitle">尺寸高：</td>
	            <td class="tableContent"><input type="text" name="sizeHeight" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">颜色：</td>
	            <td class="tableContent"><input type="text" name="color" value=""/></td>
	        
	            <td class="columnTitle">包装：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="packing" value="0" checked class="input">是
	            	<input type="radio" name="packing" value="1" class="input">否
	            </td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">包装单位：</td>
	            <td class="tableContentAuto">
	            	<input type="radio" name="packingUnit" value="PCS" class="input">只
	            	<input type="radio" name="packingUnit" value="SETS" class="input">套
	            </td>
	       
	            <td class="columnTitle">体积：</td>
	            <td class="tableContent"><input type="text" name="cbm" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">大箱尺寸长：</td>
	            <td class="tableContent"><input type="text" name="mpsizeLenght" value=""/></td>
	       
	            <td class="columnTitle">大箱尺寸宽：</td>
	            <td class="tableContent"><input type="text" name="mpsizeWidth" value=""/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">大箱尺寸高：</td>
	            <td class="tableContent"><input type="text" name="mpsizeHeight" value=""/></td>
	      
	            <td class="columnTitle">说明：</td>
	            <td class="tableContent"><input type="text" name="remark" value=""/></td>
	        </tr>	
		</table>
	</div>
 
 
</form>
</body>
</html>

