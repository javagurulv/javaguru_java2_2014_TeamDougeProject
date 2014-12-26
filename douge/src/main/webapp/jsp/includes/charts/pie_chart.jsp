<%--
  Created by IntelliJ IDEA.
  User: Sergo
  Date: 24.11.2014
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
  google.load("visualization", "1", {packages:["corechart"]});
  google.setOnLoadCallback(drawChart);
  function drawChart() {

    var data =new google.visualization.DataTable("${param.dataSet}");

    var options = {

    };

    var chart = new google.visualization.PieChart(document.getElementById(${param.divId}));

    chart.draw(data, options);
  }
</script>
<div id=${param.divId}></div>