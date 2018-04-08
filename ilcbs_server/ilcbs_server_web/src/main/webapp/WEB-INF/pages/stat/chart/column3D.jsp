<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>amCharts examples</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath }/components/newAmcharts/style.css" type="text/css">
        <%--<script src="${pageContext.request.contextPath }/components/newAmcharts/amcharts/amcharts.js" type="text/javascript"></script>--%>
        <%--<script src="${pageContext.request.contextPath }/components/newAmcharts/amcharts/serial.js" type="text/javascript"></script>--%>
        <script src="${pageContext.request.contextPath }/components/jquery-ui/jquery-1.2.6.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath }/components/eCharts/echarts.min.js" type="text/javascript"></script>
        <script>
            $(function () {
                $.ajax({
                    url: 'statChartAction_getProductSaleData',
                    dataType: 'json',
                    type: 'get',
                    success:function (value) {

                        var myChart = echarts.init(document.getElementById('chartdiv'));

                       // app.title = '坐标轴刻度与标签对齐';

                        option = {
                            color: ['#3398DB'],
                            tooltip : {
                                trigger: 'axis',
                                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                                    type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
                                }
                            },
                            grid: {
                                left: '3%',
                                right: '4%',
                                bottom: '3%',
                                containLabel: true
                            },
                            xAxis : [
                                {
                                    type : 'category',
                                    data : value.cpName,
                                    axisTick: {
                                        alignWithLabel: true
                                    }
                                }
                            ],
                            yAxis : [
                                {
                                    type : 'value'
                                }
                            ],
                            series : [
                                {
                                    name:'直接访问',
                                    type:'bar',
                                    barWidth: '60%',
                                    data:value.cpNumber
                                }
                            ]
                        };
                        myChart.setOption(option);
                    }
                })
            })
        </script>


    </head>

    <body>
        <div >
            <font >产品销售排行前15名</font>

        </div>
        <div id="chartdiv" style="width: 100%; height: 400px;"></div>

    </body>

</html>