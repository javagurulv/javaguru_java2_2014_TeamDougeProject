<%@ page import="lv.javaguru.java2.servlets.mvc.models.MVCModel" %>
<%@ page import="org.springframework.web.servlet.ModelAndView" %>
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

    String str = (String) request.getAttribute("model");
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

<div align="center">
    <form method="POST" action="users">

        <br>

        <b>Add user: </b>
        User type:
        <select name="user_typed">
            <option value="0">Admin</option>
            <option value="1">User</option>
        </select>
        login:
        <input type="Text" name="logind">
        Password:
        <input type="Text" name="passwdd">
        Comments:
        <input type="Text" name="commentsd">
        <input type="SUBMIT" value="Add user" name="adduser">

        <br><br>

        <b>Edit user: </b>
        User id:
        <input type="Text" name="useridedit">
        User type:
        <select name="user_typee">
            <option value="0">Admin</option>
            <option value="1">User</option>
        </select>
        login:
        <input type="Text" name="logine">
        Password:
        <input type="Text" name="passwde">
        Comments:
        <input type="Text" name="commentse">
        <input type="SUBMIT" value="Edit user" name="edituser">

        <br><br>

        <b>Deltete user: </b>
        <input type="Text" name="useriddelete">
        <input type="SUBMIT" value="Delete" name="delete">

    </form>
</div>

</body>
</html>
