<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../base.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/skin/default/css/left.css" media="all"/>

    <script language="javascript" src="${ctx}/js/common.js"></script>
    <script language="javascript" src="${ctx}/js/ajax/setFastMenu.js"></script>
    <script language="javascript" src="${ctx}/js/pngfix_map.js"></script>
    <script type="text/javascript" src="${ctx}/components/jquery-ui/jquery-1.2.6.js"></script>
    <script type="text/javascript" src="${ctx}/skin/default/js/toggle.js"></script>


    <script language="javascript">
        $().ready(function () {
            $(fastMenu).hide();
            //document.getElementById('aa_3').click();	//默认打开我的留言板页面
        });

        function showMenu(who) {
            if (who == "fastMenu") {
                $(fastMenu).show();
                $(customerMenu).hide();
                $.ajax({
                	   type: "get",
                	   url: "${ctx}/home/workflow/workMenuAction_fastMenu.action",
                	   //data: "name=John&location=Boston",
                	   dataType: "json",
                	   success: function(msg){
                	     //alert(  msg );
                	     $("#menuButton").empty();
                	     $.each(msg,function(i,n){
                	    	 $("#menuButton").append("<hr><a href='"+n.curl+"' target='main'>"+n.moduleName+"</a>");
                	    	 //alert(n.moduleName);
                	     });
                	     $("#menuButton").append("<hr>");
                	   }
                	});
            } else if (who == "customerMenu") {
                $(customerMenu).show();
                $(fastMenu).hide();
                $.ajax({
             	   type: "get",
             	   url: "${ctx}/home/workflow/workMenuAction_customerMenu.action",
             	   //data: "name=John&location=Boston",
             	   dataType: "json",
             	   success: function(msg){
             	     //alert(  msg );
             	     $("#menuButton").empty();
             	     $.each(msg,function(i,n){
             	    	 $("#menuButton").append("<hr><a href='"+n.curl+"' target='main'>"+n.moduleName+"</a>");
             	    	 //alert(n.moduleName);
             	     });
             	     $("#menuButton").append("<hr>");
             	   }
             	});
            }
        }
        function clearFastMenue(){
        	 //alert("aaa");
        	$.ajax({
         	   type: "get",
         	   url: "${ctx}/home/workflow/workMenuAction_clearFastMenue.action",
         	   //data: "name=John&location=Boston",
         	   dataType: "json",
         	   success: function(msg){
         		   
         	   }
         	});
        	$("#menuButton").empty()
        	//alert("vvvv");
        }
    </script>


</head>

<body id="left_frame">
<div class="PositionFrame_black" id="PositionFrame"></div>


<div id="sidebar" class="sidebar">
    <div class="sidebar_t">
        <div class="sidebar_t_l"></div>
        <div class="sidebar_t_c"></div>
        <div class="sidebar_t_r"></div>
    </div>
    <div class="panel">
        <div class="panel_icon"><img src="${ctx}/skin/default/images/icon/user2.png"/></div>
        <div class="panel-header">
            <div class="panel-title">个人工作台</div>
            <div class="panel-content">
                <ul>
                    <li><a href="${ctx}/home/workflow/messageBoardAction_tasklist.action" target="main" id="aa_3" onclick="linkHighlighted(this)">我的留言板</a></li>
                    <li><a href="${ctx}/home/workflow/taskWaitAction_tasklist.action" target="main" id="aa_2" onclick="linkHighlighted(this)">我的代办任务</a></li>
                    <li><a href="${ctx}/home/workflow/feedbackAction_feedbackList.action" target="main" id="aa_1" onclick="linkHighlighted(this)">意见反馈</a></li>
                </ul>
            </div>

        </div>
    </div>
    <div class="sidebar_t">
        <div class="sidebar_b_l"></div>
        <div class="sidebar_t_c"></div>
        <div class="sidebar_b_r"></div>
    </div>
</div>


<div id="sidebar" class="sidebar">
    <div class="sidebar_t">
        <div class="sidebar_t_l"></div>
        <div class="sidebar_t_c"></div>
        <div class="sidebar_t_r"></div>
    </div>
    <div class="panel">
        <div class="panel_icon"><img src="${ctx}/skin/default/images/icon/cubes.png"/></div>
        <div class="panel-header">
            <div class="panel-title">我的常用功能</div>
            <div style="margin-top:5px;"></div>
            <!-- 以上为永久固定栏目，以下为活动栏目 -->
            <div style="border-bottom:1px dotted #cee1df;">
                切换:<a href="#" onmousemove="javascript:showMenu('fastMenu');">快捷菜单</a>
                /
                <a href="#" onmousemove="javascript:showMenu('customerMenu');">自定义菜单</a>
            </div>
           <div id="menuButton">
           
           </div>
            <div id="fastMenu">
                <div class="panel-content"></div>
                <a href="#" onclick="javascript:clearFastMenue()" class="DelFastMenu"><font color="gray">清除常用功能列表</font></a>
            </div>

            <div id="customerMenu">
                <div class="FastMenu">
                	<img src="${ctx}/skin/default/images/notice.gif" style="margin-right:5px;" border="0"/>
                	<font color="gray"><a href="${ctx}/home/workflow/workMenu_toCustmorMenu.action" target="main">重新定义您的菜单</a></font>
                </div>
            </div>
        </div>
    </div>
    <div class="sidebar_t">
        <div class="sidebar_b_l"></div>
        <div class="sidebar_t_c"></div>
        <div class="sidebar_b_r"></div>
    </div>
</div>


<!-- begin1  -->
<div id="sidebar" class="sidebar">
    <div class="sidebar_t">
        <div class="sidebar_t_l"></div>
        <div class="sidebar_t_c"></div>
        <div class="sidebar_t_r"></div>
    </div>
    <div class="panel">
        <div class="panel_icon"><img src="${ctx}/skin/default/images/icon/businessman2.png"/></div>
        <div class="panel-header">
            <div class="panel-title">
                用户设定
            </div>

            <div class="panel-content">
                <ul>

                    <li><a href="${ctx}/home/workflow/userInfoAction_toUpdate" id="aa_2" onclick="linkHighlighted(this)" target="main">个人信息修改</a></li>
                    <li><a href="${ctx}/feedback/fankuiAction_toBack" id="aa_2" onclick="linkHighlighted(this)" target="main">系统使用反馈</a></li>

                </ul>
            </div>
        </div>
    </div>
    <div class="sidebar_t">
        <div class="sidebar_b_l"></div>
        <div class="sidebar_t_c"></div>
        <div class="sidebar_b_r"></div>
    </div>
</div>
<!-- end1 -->

</body>
</html>