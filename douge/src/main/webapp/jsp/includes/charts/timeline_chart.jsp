<%--
  Created by IntelliJ IDEA.
  User: Sergo
  Date: 20.12.2014
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
  google.load("visualization", "1", {packages:["timeline"]});
  google.setOnLoadCallback(drawChart);
  function drawChart() {

    var data =new google.visualization.DataTable("${param.dataset}");

    var chart = new google.visualization.Timeline(document.getElementById("${param.divId}"));

    chart.draw(data);
  }
</script>
<div id="${param.divId}"></div>