<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form name="icform" method="post">
	<%-- <input type="hidden" name="id" value="${id }"/> --%>
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('invoiceAction_insert','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   新增发票
  </div> 
  

 
    <div>
    
    <table class="commonTable" cellspacing="1">
	        <tr>
	             <td class="columnTitle">发票编号：</td>
	            <td class="tableContent"><input type="text" name="blNo" value=""/></td>
	        </tr>		
	        <tr>
	            <td class="columnTitle">贸易条款：</td>
	            <td class="tableContent"><input type="text" name="tradeTerms" value=""/></td>
	        </tr>		
	         <tr>
	            <td class="columnTitle">合同编号SC_NO：</td>
	            <td class="tableContent"><input type="text" name="scNo" value=""/></td>
	        </tr>	
		</table>
		
	</div>
	
		 <div class="textbox-title">
        <img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
        委托列表
    </div>

    <div>


        <div class="eXtremeTable">
            <table id="ec_table" class="tableRegion" width="98%">
                <thead>
                <tr>
                    <td class="tableHeader">序号</td>
                    <td class="tableHeader">编号</td>
                    <td class="tableHeader">运输方式</td>
                    <td class="tableHeader">货主</td>
                    <td class="tableHeader">提单抬头</td>
                    <td class="tableHeader">正本通知人</td>
                    <td class="tableHeader">信用证</td>
                    <td class="tableHeader">装运港</td>
                    <td class="tableHeader">转船港</td>
                    <td class="tableHeader">卸货港</td>
                    <td class="tableHeader">状态</td>


                </tr>
                </thead>
                <tbody class="tableBody">
             

                <c:forEach items="${shippingOrders}" var="o" varStatus="status">
                    <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
                        <td><input type="checkbox" name="id" value="${o.id}"/></td>
                        <td>${status.index+1}</td>
                        <td>${o.id}</td>
                        <td>${o.orderType}</td>
                        <td>${o.shipper}</td>
                        <td>${o.consignee}</td>
                        <td>${o.notifyParty}</td>
                        <td>${o.lcNo}</td>
                        <td>${o.portOfLoading}</td>
                        <td>${o.portOfTrans}</td>
                        <td>${o.portOfDischarge}</td>

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
	
	
	
	
	<%-- 
		<div class="textbox-title">
     		装箱单
   		</div>    
        <div class="eXtremeTable">
          
            <table  class="tableRegion" width="70%">
                <thead>
                <tr>
                   
                    <td class="tableHeader">序号</td>
                    <td class="tableHeader">卖方</td>
                    <td class="tableHeader">买方</td>
                    <td class="tableHeader">发票号</td>
                    <td class="tableHeader">发票日期</td>
                    <td class="tableHeader">状态</td>
                </tr>
                </thead>
                <tbody class="tableBody">
                

                <c:forEach items="${packingList}" var="o" varStatus="status">
                    <tr >
                        <td><input type="radio" name="id" value="${o.id}"/></td>
                        <td>${status.count}</td>

                        <td>${o.seller}</td>
                        <td>${o.buyer}</td>
                        <td>${o.invoiceNo}</td>
                        <td>
                            <fmt:formatDate value="${o.invoiceDate}" pattern="yyyy-MM-dd"/>
                        </td>

                        <td>
                            <c:if test="${o.state==0}">草稿</c:if>
                            <c:if test="${o.state==1}">已装箱</c:if>
                            <c:if test="${o.state==2}">已委托</c:if>
                            <c:if test="${o.state==3}">已通知</c:if>
                        </td>

                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div> --%>
	
 
 
</form>
</body>
</html>

