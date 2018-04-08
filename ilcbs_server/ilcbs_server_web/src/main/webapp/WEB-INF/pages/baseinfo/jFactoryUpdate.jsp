<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript" src="${ctx}/components/jquery-ui/jquery-1.2.6.js"></script>
    <script type="text/javascript" src="${ctx}/js/tabledo.js"></script>
    <script type="text/javascript" src="${ctx}/js/datepicker/WdatePicker.js"></script>

    <script language="JavaScript">
        $(document).ready(function () {
            <%--${mRecordData}--%>
            //发送ajax请求-------------返回json------------后面就去组织数据（调用函数）
            //当进入更新页面时-----------直接获取服务器返回的串   [{"id":"1","productNo":""},{"id":"1","productNo":""},{"id":"1","productNo":""}]
            //addTRRecord("mRecordTable", 'n.id', 'n.productNo', 'n.cnumber', 'n.grossWeight', 'n.netWeight', 'n.sizeLength',' n.sizeWidth', 'n.sizeHeight', 'n.exPrice', 'n.tax');
          $.ajax({
                url:'FactoryAction_getTableData?id=${id}',
                type:'get',
                dataType:'json',
                success:function (value) {
                    //alert(JSON.stringify(value));
                    $.each(value,function (i,n) {
                       // alert(n.cnumber);
                        addTRRecord("mRecordTable", n.id, n.factoryName, n.productNo, n.loadingRate, n.boxNum, n.packingUnit, n.cnumber, n.price, n.amoun)
                    })

                }
            })
        });


        /* 实现表格序号列自动调整 created by wyj 20081219 */
        function sortnoTR() {
            sortno('mRecordTable', 2, 1);
        }

        function addTRRecord(objId,id, factoryName, productNo, loadingRate, boxNum, packingUnit, cnumber, price, amoun) {

            var _tmpSelect = "";
            var tableObj = document.getElementById(objId);
            var rowLength = tableObj.rows.length;

            oTR = tableObj.insertRow();

            oTD = oTR.insertCell(0);
            oTD.style.whiteSpace = "nowrap";
            oTD.ondragover = function () {
                this.className = "drag_over"
            };	//动态加事件, 改变样式类
            oTD.ondragleave = function () {
                this.className = "drag_leave"
            };
            oTD.onmousedown = function () {
                clearTRstyle("result");
                this.parentNode.style.background = '#0099cc';
            };
            //this.style.background="#0099cc url(../images/arroww.gif) 4px 9px no-repeat";
            oTD.innerHTML = "&nbsp;&nbsp;";
            oTD = oTR.insertCell(1);
            oTD.innerHTML = "<input class=\"input\" type=\"checkbox\" name=\"del\" value=\"" + id + "\"><input type=\"hidden\" name=\"mr_id\" value=\"" + id + "\"><input class=\"input\" type=\"hidden\" id=\"mr_changed\" value=\"0\" name=\"mr_changed\">";
            oTD = oTR.insertCell(2);
            oTD.innerHTML = "<input class=\"input\" type=\"text\" name=\"mr_factoryName\" readonly size=\"3\" value=\"\">";
            oTD = oTR.insertCell(3);
            oTD.innerHTML = "<b><font face='微软雅黑'><font color='blue'>" + productNo; +"</font></font></b> "
            oTD = oTR.insertCell(4);
            oTD.innerHTML = "<input type=\"text\" name=\"mr_loadingRate\" maxLength=\"10\" value=\"" + cnumber + "\" onchange=\"setUpdate(this);\" size=\"15\">";
            oTD = oTR.insertCell(5);
            oTD.innerHTML = "<input type=\"text\" name=\"mr_boxNum\" maxLength=\"10\" value=\"" + grossWeight + "\" onchange=\"setUpdate(this);\" size=\"15\">";
            oTD = oTR.insertCell(6);
            oTD.innerHTML = "<input type=\"text\" name=\"mr_packingUnit\" maxLength=\"10\" value=\"" + netWeight + "\" onchange=\"setUpdate(this);\" size=\"15\">";
            oTD = oTR.insertCell(7);
            oTD.innerHTML = "<input type=\"text\" name=\"mr_cnumber\" maxLength=\"10\" value=\"" + sizeLength + "\" onchange=\"setUpdate(this);\" size=\"15\">";
            oTD = oTR.insertCell(8);
            oTD.innerHTML = "<input type=\"text\" name=\"mr_price\" maxLength=\"10\" value=\"" + sizeWidth + "\" onchange=\"setUpdate(this);\" size=\"15\">";
            oTD = oTR.insertCell(9);
            oTD.innerHTML = "<input type=\"text\" name=\"mr_amount\" maxLength=\"10\" value=\"" + sizeHeight + "\" onchange=\"setUpdate(this);\" size=\"15\">";
            dragtableinit();	//拖动表格行
            sortnoTR();			//排序号
        }

        function setUpdate(obj) {
            var currTr = obj.parentNode.parentNode;
            if (obj.value != obj.defaultValue) {	//当填写的框内容发生变化时,设置本行记录发生变化标识
                //currTr.childNodes[1].childNodes[2].value = "1";//这个也可以用
                currTr.getElementsByTagName("input")[2].value = "1";
            }
        }

    </script>

</head>

<body>
<form name="icform" method="post">
	<input type="hidden" name="id" value="${id}"/>

<div id="menubar">
	<div id="middleMenubar">
		<div id="innerMenubar">
		  <div id="navMenubar">
			<ul>
				<li id="save"><a href="#" onclick="formSubmit('FactoryAction_update','_self');this.blur();">保存</a></li>
				<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
			</ul>
		  </div>
		</div>
	</div>
</div>
   
  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   修改厂家信息
  </div> 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">工厂全名：</td>
	            <td class="tableContent"><input type="text" name="fullName" value="${fullName }"/></td>
	            <td class="columnTitle">工厂简称：</td>
	            <td class="tableContent"><input type="text" name="factoryName" value="${factoryName }"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">联系人</td>
	            <td class="tableContent"><input type="text" name="contacts" value="${contacts}"/></td>
	            <td class="columnTitle">联系电话：</td>
	            <td class="tableContent"><input type="text" name="phone" value="${phone}"/></td>
	        </tr>	
	        <tr>
	            <td class="columnTitle">手机号码：</td>
	            <td class="tableContent"><input type="text" name="mobile" value="${mobile}"/></td>
	            <td class="columnTitle">传真：</td>
	            <td class="tableContent"><input type="text" name="fax" value="${fax}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle">厂家地址：</td>
	            <td class="tableContent"><input type="text" name="address" value="${address}"/></td>
	            <td class="columnTitle">检察员：</td>
	            <td class="tableContent"><input type="text" name="inspector" value="${inspector}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle">备注：</td>
	            <td class="tableContent"><input type="text" name="remark" value="${remark}"/></td>
	            <td class="columnTitle">状态：</td>
	            <td class="tableContent"><input type="text" name="state" value="${state}"/></td>
	        </tr>
		</table>
		    <div class="listTablew">
        <table class="commonTable_main" cellSpacing="1" id="mRecordTable">
            <tr class="rowTitle" align="middle">
                <td width="25" title="可以拖动下面行首,实现记录的位置移动.">
                    <img src="${ctx }/images/drag.gif">
                </td>
                <td width="20">
                    <input class="input" type="checkbox" name="ck_del" onclick="checkGroupBox(this);"/>
                </td>
                <td width="33">序号</td>
                <td>厂家</td>
                <td>货号</td>
                <td>装率</td>
                <td>箱数</td>
                <td>包装单位</td>
                <td>数量</td>
                <td>单价</td>
                <td>总金额</td>
            </tr>
        </table>
    </div>


    <div>
        <div class="textbox-bottom">
            <div class="textbox-inner-bottom">
                <div class="textbox-go-top">
                </div>
            </div>
        </div>
    </div>
		
	</div>
</form>
</body>
</html>

