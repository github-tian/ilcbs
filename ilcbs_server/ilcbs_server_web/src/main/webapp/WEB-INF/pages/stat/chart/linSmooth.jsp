<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>amCharts examples</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/components/newAmcharts/style.css" type="text/css">
    <script src="${pageContext.request.contextPath }/components/jquery-ui/jquery-1.2.6.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath }/components/eCharts/echarts.min.js" type="text/javascript"></script>

    <script>
        $(function () {
            $.ajax({
                url: 'statChartAction_getOnlineinfoData',
                dataType: 'json',
                type: 'get',
                success: function (oValue) {
                    //alert(oValue.oTimeStart);
                    var myChart = echarts.init(document.getElementById('chartdiv'));
                    option = {
                        title: {
                            text: '折线图堆叠'
                        },
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: ['系统访问压力图']
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        toolbox: {
                            feature: {
                                saveAsImage: {}
                            }
                        },
                        xAxis: {
                            // type: 'category',
                            // boundaryGap: false,
                            // data: oValue.oTime
                            type: 'category',
                            boundaryGap: false,
                            data: oValue.oTime
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [
                            {
                                // name:'系统访问压力图',
                                // type:'line',
                                // stack: '总量',
                                // data:oValue.oTimes
                                name: '系统访问压力图',
                                type: 'line',
                                stack: '总量',
                                data: oValue.oTimes
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
<div id="chartdiv" style="width:100%; height:400px;"></div>
</body>

</html>