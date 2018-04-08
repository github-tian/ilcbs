<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>amCharts examples</title>
    <script src="${pageContext.request.contextPath }/components/jquery-ui/jquery-1.2.6.js"
            type="text/javascript"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/components/newAmcharts/style.css" type="text/css">
    <script src="${pageContext.request.contextPath }/components/newAmcharts/amcharts/amcharts.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath }/components/newAmcharts/amcharts/pie.js"
            type="text/javascript"></script>

    <script>

        $(function () {
            var chart;

            $.ajax({
                url: 'statChartAction_getFactorySaleData',
                dataType: 'json',
                type: 'get',
                success: function (value) {
                    // alert("qqqq");
                    /* var chartData = [
                        {
                            "country": "United States",
                            "visits": 9252
                        },
                        {
                            "country": "China",
                            "visits": 1882
                        }
                    ]; */
                    var chartData = value;
                    // PIE CHART
                    chart = new AmCharts.AmPieChart();

                    // title of the chart
                    chart.addTitle("统计销售量的前15名", 16);

                    chart.dataProvider = chartData;
                    chart.titleField = "productNo";//===============================
                    chart.valueField = "amount";//==================================
                    chart.sequencedAnimation = true;
                    chart.startEffect = "elastic";
                    chart.innerRadius = "40%";
                    chart.startDuration = 5;
                    chart.labelRadius = 15;
                    chart.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";
                    // the following two lines makes the chart 3D
                    chart.depth3D = 10;
                    chart.angle = 15;
                    chart.creditsPosition = "top-right";
                    // WRITE
                    chart.write("chartdiv");

                }
            });
        });



        <%--var chart;--%>
        <%--var chartData = ${myChartData};--%>
        <%--/* var chartData = [--%>
        <%--{--%>
        <%--"country": "United States",--%>
        <%--"visits": 9252--%>
        <%--},--%>
        <%--{--%>
        <%--"country": "China",--%>
        <%--"visits": 1882--%>
        <%--}--%>
        <%--]; */--%>

        <%--AmCharts.ready(function () {--%>
        <%--// PIE CHART--%>
        <%--chart = new AmCharts.AmPieChart();--%>

        <%--// title of the chart--%>
        <%--chart.addTitle("统计销售量的前15名", 16);--%>

        <%--chart.dataProvider = chartData;--%>
        <%--chart.titleField = "productNo";//===============================--%>
        <%--chart.valueField = "amount";//==================================--%>
        <%--chart.sequencedAnimation = true;--%>
        <%--chart.startEffect = "elastic";--%>
        <%--chart.innerRadius = "40%";--%>
        <%--chart.startDuration = 5;--%>
        <%--chart.labelRadius = 15;--%>
        <%--chart.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>";--%>
        <%--// the following two lines makes the chart 3D--%>
        <%--chart.depth3D = 10;--%>
        <%--chart.angle = 15;--%>

        <%--chart.creditsPosition = "top-right";--%>

        <%--// WRITE--%>
        <%--chart.write("chartdiv");--%>
        <%--});--%>
    </script>
</head>

<body>
<div id="chartdiv" style="width:100%; height:100%;position: absolute;left: -10px;top: 10px"></div>
</body>

</html>