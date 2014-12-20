<%--
  Created by IntelliJ IDEA.
  User: Sergo
  Date: 20.12.2014
  Time: 19:48
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
      title: 'Company Performance',
      vAxis: {title: 'Year',  titleTextStyle: {color: 'red'}}
    };

    var chart = new google.visualization.BarChart(document.getElementById(${param.divId}));

    chart.draw(data, options);
  }
</script>
<div id=${param.divId}></div>
