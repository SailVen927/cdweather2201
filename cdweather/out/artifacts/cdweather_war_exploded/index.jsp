<%--
  Created by IntelliJ IDEA.
  User: 朱语凡
  Date: 2022/1/8
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
  <title>$Title$</title>
  <link rel="stylesheet" type="text/css" href="css/box.css">
  <script src="echarts.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <script src="https://cdn.bootcss.com/echarts/4.4.0-rc.1/echarts.min.js"></script>
  <style type="text/css">
    .title{
      height: 100px;
      width: 100%;
      background-color: #66ccff;
    }
    .title_content{
      height: 80px;
      width: 600px;
      /*background-color: blue;*/
      font-family: "Microsoft YaHei UI Light";
      font-size: 30px;
      color: aliceblue;
    }
    .body{
      height: 100%;
      width: 100%;
    }
    .body_nav {
      text-align: center;
      width: 140px;
      height: 600px;
      /* margin: 20px; */
      padding-top: 40px;
      background-color: #afcdeb;
      float: left;
      border-radius: 10px;
      margin-top: 50px;
      margin-left: 50px;
    }
    .body_data{
      height: 150%;
      width: 1000px;
      margin-right: 40px;
      padding-left: 100px;
      float: right;
      /*background-color: black;*/
      display: block;
    }
    #body_nav_text{
      text-align: center;

    }
    a{
      text-decoration:none;
      font-size: 16px;
      font-weight: bolder;
      color: white;
      margin:0;
      padding-top: 10px;
      display:block;
      height:40px;
    }
    a:hover{
      background:#66ccff;
    }
    .chart_data{
      /*background-color: purple;*/
      width: 1000px;
      height: 500px;
    }
    .temp_data{
      background-color: white;
      float: left;
      width: 1000px;
      height: 400px;
      margin-top: 50px;
      margin-right: 40px;
      border-radius: 10px;
    }
    .pie_data{
      /*background-color: green;*/
      float: right;
      width: 400px;
      height: 400px;
    }
    .all_data{
      background-color: white;
      margin-top: 30px;
      border-radius: 10px;
      width: 1000px;
      height: 500px;
    }
    .td{
      width:225px;
      border:solid #add9c0;
      border-width:0px 1px 1px 0px;
      padding:10px 0px;
    }
    .table{
      width:900px;
      table-border:3px;
      table-height:30px;
      margin-top:50px;
    }


  </style>

</head>
<body>
<div class="title">
  <div class="title_content" >
    成都历史天气
  </div>
</div>
<div class="body">
  <div class="body_nav">
    <nav id="body_nav_text">
      <a href="yearDataServlet?data=2011">2011年</a>
      <a href="yearDataServlet?data=2012">2012年</a>
      <a href="yearDataServlet?data=2013">2013年</a>
      <a href="yearDataServlet?data=2014">2014年</a>
      <a href="yearDataServlet?data=2015">2015年</a>
      <a href="yearDataServlet?data=2016">2016年</a>
      <a href="yearDataServlet?data=2017">2017年</a>
      <a href="yearDataServlet?data=2018">2018年</a>
      <a href="yearDataServlet?data=2019">2019年</a>
      <a href="yearDataServlet?data=2020">2020年</a>
    </nav>
  </div>
  <div class="body_data">
    <div class="chart_data">
      <div class="temp_data" id="temp_data">
        <script>
            function maxtemp(alltemp) {
                return parseInt(alltemp.split("/")[0].replace("℃",""));
            }
            function mintemp(alltemp) {
                return parseInt(alltemp.split("/")[1].replace("℃",""));
            }
            var myChart = echarts.init(document.getElementById("temp_data"));

            option = {
                title : {
                    text: '历史气温走势',
                },
                legend: {
                    data:['最高气温','最低气温']
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis: {
                    type: 'category',
                    boundaryGap : false,
                    data: [
                        <c:forEach var="w" items="${weathers}">
                        '${w.wdate}',
                        </c:forEach>
                    ]
                },
                yAxis: {
                    type: 'value',
                    axisLabel : {
                        formatter: '{value} °C'
                    }
                },
                series: [{
                    name:"最高气温",
                    type:'line',
                    data: [
                        <c:forEach var="w" items="${weathers}">
                        maxtemp('${w.temperature}'),
                        </c:forEach>
                    ],
                    type: 'line'
                },{
                    name:"最低气温",
                    type:'line',
                    data: [
                        <c:forEach var="w" items="${weathers}">
                        mintemp('${w.temperature}'),
                        </c:forEach>
                    ],
                    type: 'line'

                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        </script>
      </div>
    </div>
    <div class="all_data">
      <table>
        <tr>
          <td>日期</td>
          <td>天气状况</td>
          <td>气温</td>
          <td>风力风向</td>
        </tr>

        <c:forEach var="w" items="${weathers}" >
          <tr>
            <td style="width: 200px">${w.wdate}</td>
            <td style="width: 200px">${w.weather}</td>
            <td style="width: 200px">${w.temperature}</td>
            <td style="width: 400px">${w.wind}</td>
            <%--<td>parseInt(${w.temperature}.split("/")[0].replace("℃",""));</td>--%>
          </tr>
        </c:forEach>
      </table>

    </div>

  </div>
</div>
</body>
</html>

