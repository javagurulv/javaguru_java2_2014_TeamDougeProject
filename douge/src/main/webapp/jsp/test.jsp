<%@ page import="com.google.visualization.datasource.datatable.DataTable" %>
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
    <script type="text/javascript" src="https://www.google.com/jsapi">

    </script>
</head>
<body>
<%   String str =(String)request.getAttribute("model");
out.print(str);%>
<script type="text/javascript">
  /*  google.load("visualization", "1", {packages:["table"]});
    google.setOnLoadCallback(drawTable);

    function drawTable() {
        var data = new google.visualization.DataTable(<% out.print(str); %>);
        var table = new google.visualization.Table(document.getElementById('table_div'));
        table.draw(data, {showRowNumber: true});
    }  */

</script>
<div id = "table_div" style = "width:500px;">

</div>

<script type="text/javascript">


  /*  google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChart);
    function drawChart() {
        var data =  new google.visualization.DataTable(<% out.print(str); %>);

        var options = {
            title: 'Company Performance',
            vAxis: {title: 'Year',  titleTextStyle: {color: 'red'}}
        };

        var chart = new google.visualization.BarChart(document.getElementById('chart_div'));

        chart.draw(data, options);
    } */

</script>
<div id = "chart_div" style = "width:500px;">

</div>

</body>
</html>
