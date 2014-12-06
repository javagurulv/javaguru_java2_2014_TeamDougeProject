<%@ page import="com.google.visualization.datasource.datatable.DataTable" %>
<%@ page import="com.google.visualization.datasource.render.JsonRenderer" %>
<%@ page import="org.springframework.ui.Model" %>
<%@ page import="lv.javaguru.java2.servlets.mvc.models.MVCActorModel" %>
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
    MVCActorModel actorModel =(MVCActorModel)request.getAttribute("model");
    String str = actorModel.getData().toString();
    Integer interval = actorModel.getInterval();
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
<br>
<div align="center">

<%
 for (int i=0;i<interval;i++){
     if(request.getParameter("interval")!=null && Integer.valueOf(request.getParameter("interval"))==i){
         out.print(i+1);
     } else{
        %>
<a href="/actors?interval=<%out.print(i);%>"><%out.print(i+1);%></a>
   <% }}
%>
</div>
</body>
</html>
