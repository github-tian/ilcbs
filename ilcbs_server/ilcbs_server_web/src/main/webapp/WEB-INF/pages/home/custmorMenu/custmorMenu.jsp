<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../baselist.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title></title>
	<link rel="stylesheet" href="${ctx }/components/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>

    <script type="text/javascript" src="${ctx }/components/zTree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${ctx }/components/zTree/js/jquery.ztree.core-3.5.min.js"></script>
    <script type="text/javascript" src="${ctx }/components/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
	<script type="text/javascript">
        var zTreeObj;
        var setting = {
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };

        $(function () {
            $.ajax({
                url: "${ctx}/home/workflow/workMenuAction_genzTreeNodes.action",
                type: "GET",
                dataType: "json",
                success: initzTree
            });
        });

        //回调函数
        function initzTree(data) {
            //初始化树
            zTreeObj = $.fn.zTree.init($("#jkTree"), setting, data);

            //展开树结点
            zTreeObj.expandAll(true);
        }

        //获取所有选择的节点
        function submitCheckedNodes() {
            var nodes = new Array();
            nodes = zTreeObj.getCheckedNodes(true);		//取得选中的结点
            var str = "";
            for (i = 0; i < nodes.length; i++) {
                if (str != "") {
                    str += ",";
                }
                str += nodes[i].id;
            }
            $('#moduleIds').val(str);
        }
    </script>	
</head>

<body>
<form name="icform" method="post">
    <input type="hidden" id="moduleIds" name="moduleIds" value=""/>
    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="save"><a href="#" onclick="submitCheckedNodes();formSubmit('workMenuAction_saveCustomerMenu.action','_self');this.blur();">保存</a>
                        </li>
                        <!-- <li id="back"><a href="#" onclick="formSubmit('roleAction_list','_self');this.blur();">返回</a>
                        </li> -->
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">
                    配置您自己的跳转菜单
                </div>
            </div>
        </div>

        <%-- <div>


        <div style="text-align:left">
            <c:forEach items="${moduleList}" var="o">
                <div style="padding:3px;">
                <input type="checkbox" name="moduleIds" value="${o.id}" class="input"
                    <c:if test="${fn:contains(roleModuleStr,o.id)}">checked</c:if>
                >
                ${o.name}
                </div>
            </c:forEach>
        </div>

        </div> --%>
        <div>
            <ul id="jkTree" class="ztree">

            </ul>
        </div>


</form>
</body>
</html>