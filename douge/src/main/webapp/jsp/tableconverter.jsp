<%@ page import="com.google.visualization.datasource.datatable.DataTable" %>
<%@ page import="com.google.visualization.datasource.render.JsonRenderer" %>
<%--
  Created by IntelliJ IDEA.
  User: Juris
  Date: 24.11.2014
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>

</head>
<body>
<%
DataTable dataTable=(DataTable)request.getAttribute("model");
CharSequence str1 = JsonRenderer.renderDataTable(dataTable, true, true, true);
%>

<script type="text/javascript">
    google.load("visualization", "1", {packages:["table"]});
    google.setOnLoadCallback(drawTable);



    function drawTable() {
        var data = new google.visualization.DataTable(<%out.print(str1);%>);

        var table = new google.visualization.Table(document.getElementById('table_div'));

        table.draw(data, {showRowNumber: true});
    }
</script>
<div id="table_div"></div>
</body>
</html>
