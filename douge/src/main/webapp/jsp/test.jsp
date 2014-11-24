<%--
  Created by IntelliJ IDEA.
  User: Sergo
  Date: 24.11.2014
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
</head>
<body>
<%
    for(int i = 0; i < 2; i++){
    %>

<script type="text/javascript">
    google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChart);
    function drawChart() {

        var data = google.visualization.arrayToDataTable([
            ['Task', 'Hours per Day'],
            ['Work',     11],
            ['Eat',      2],
            ['Commute',  2],
            ['Watch TV', 2],
            ['Sleep',    7]
        ]);

        var options = {
            title: 'My Daily Activities'
        };

        var chart = new google.visualization.PieChart(document.getElementById('div<% out.print(String.valueOf(i)); %>'));

        chart.draw(data, options);
    }
</script>
<div id="div<% out.print(String.valueOf(i)); %>"></div>
<%
}
%>
</body>
</html>
