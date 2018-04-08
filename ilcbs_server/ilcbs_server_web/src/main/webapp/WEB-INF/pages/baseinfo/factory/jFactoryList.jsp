<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
		<style>
   
        .sel_btn{
            height: 21px;
            line-height: 21px;
            padding: 0 11px;
            background: #02bafa;  /* #02bafa */
            border: 1px #26bbdb solid;
            border-radius: 3px;
            /*color: #fff;*/
            display: inline-block;
            text-decoration: none;
            font-size: 12px;
            outline: none;
        }
        .ch_cls{
            background: #e4e4e4;
        }
    
</style>
	</head>
	
	<body>
		<form name="icform" method="post">
		
			<div id="menubar">
				<div id="middleMenubar">
					<div id="innerMenubar">
					  <div id="navMenubar">
						<ul>
						<li id="view"><a href="#" onclick="formSubmit('factoryAction_toview','_self');this.blur();">查看</a></li>
						<li id="new"><a href="#" onclick="formSubmit('factoryAction_tocreate','_self');this.blur();">新增</a></li>
						<li id="update"><a href="#" onclick="formSubmit('factoryAction_toupdate','_self');this.blur();">修改</a></li>
						<li id="delete"><a href="#" onclick="formSubmit('factoryAction_delete','_self');this.blur();">删除</a></li>
						</ul>
					  </div>
					</div>
				</div>
			</div>
			   
			  <div class="textbox-title">
				<img src="/skin/default/images/icon/currency_yen.png"/>
			    厂家列表
			  </div> 
			  
			<div>
			
			
			<div class="eXtremeTable" >
			<table id="ec_table" class="tableRegion" width="98%" >
				<thead>
				<tr>
					<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
					<td class="tableHeader">序号</td>
					<td class="tableHeader">编号</td>
					<td class="tableHeader">产品类型</td>
					<td class="tableHeader">厂家名称</td>
					<td class="tableHeader">联系人</td>
					<td class="tableHeader">电话</td>
					<td class="tableHeader">地址</td>
					<td class="tableHeader">合作状态</td>
					<td class="tableHeader">公司产品</td>
				</tr>
				</thead>
				<tbody class="tableBody" >
			${links}
				
				<c:forEach items="${results}" var="o" varStatus="status">
				<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
					<td><input type="checkbox" name="id" value="${o.id}"/></td>
					<td>${status.index+1}</td>
					<td>${o.id}</td>
					<td>
					${o.ctype }
					</td>
					<td>${o.factoryName}</td>
					<td>${o.contacts}</td>
					<td>${o.phone}</td>																			
					<td>${o.address}</td>
					<td>
						<c:if test="${o.state == 0}">未合作</c:if>
						<c:if test="${o.state == 1}"><font color="green">合作</font></c:if>
						<c:if test="${o.state == 2}"><font color="red">已解除合作</font></c:if>
					</td>
					<td><a href="${ctx }/baseinfo/productAction_tolist?fid=${o.id}" style="color:white" class="sel_btn" id="sel_btn1">产品列表</a></td>
				</tr>
				</c:forEach>
				</tbody>
			</table>
			</div>
			 
			</div>
		 
		 
		</form>
	</body>
</html>

