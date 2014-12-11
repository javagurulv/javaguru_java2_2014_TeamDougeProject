<%@ page import="lv.javaguru.java2.servlets.mvc.models.MVCModel" %>
<%--
  Created by IntelliJ IDEA.
  User: Juris
  Date: 10.12.2014
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>

<head>
    <title></title>
</head>
<body>
<%
    String str =(String)request.getAttribute("model");
%>

<script type="text/javascript">
    google.load("visualization", "1", {packages:["table"]});
    google.setOnLoadCallback(drawTable);



    function drawTable() {
        var data = new google.visualization.DataTable(<%out.print(str);%>);

        var table = new google.visualization.Table(document.getElementById('table_div'));

        table.draw(data, {showRowNumber: true});
    }
</script>
<div id="table_div"></div>

</body>
</html>
