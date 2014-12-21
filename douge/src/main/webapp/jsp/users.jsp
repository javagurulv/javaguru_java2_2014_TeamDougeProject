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
    String sessionLogin = (String) session.getAttribute("sessionLogin");
    Long userType = (Long) session.getAttribute("userType");

    if (session.getAttribute("sessionLogin") == null || userType != 0) {
            response.sendRedirect("/login");
    }

%>
<div align="right">Welcome, <%= sessionLogin %>! &nbsp <a href="/home">Home</a> &nbsp <a href="/logout">Log Out</a></div><br>

<script type="text/javascript">
    google.load("visualization", "1", {packages:["table"]});
    google.setOnLoadCallback(drawTable);



    function drawTable() {
        var data = new google.visualization.DataTable("<%out.print(str);%>");

        var table = new google.visualization.Table(document.getElementById('table_div'));

        table.draw(data, {showRowNumber: false});
    }
</script>
<div id="table_div"></div>

    <form method="POST" action="users">

        <br>

        <Table width="100%">
            <tr>
                <td><b>Add user: </b></td>
                <td></td>
                <td><b>Edit user: </b></td>
                <td></td>
                <td><b>Delete user: </b></td>
                <td></td>
            </tr>
            <tr>
                <td>User type:</td>
                <td><select name="user_typed">
                        <option value="0">Admin</option>
                        <option value="1">User</option>
                    </select></td>
                <td>User id:</td>
                <td> <input type="Text" name="useridedit"></td>
                <td>User id:</td>
                <td> <input type="Text" name="useriddelete"></td>
            </tr>
            <tr>
                <td>login:</td>
                <td><input type="Text" name="logind"></td>
                <td> User type:</td>
                <td><select name="user_typee">
                        <option value="0">Admin</option>
                        <option value="1">User</option>
                    </select></td>
                <td><input type="SUBMIT" value="Delete" name="delete"></td>
                <td></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="Text" name="passwdd"></td>
                <td>login:</td>
                <td><input type="Text" name="logine"></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>Comments:</td>
                <td><input type="Text" name="commentsd"></td>
                <td>Password:</td>
                <td><input type="Text" name="passwde"></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td><input type="SUBMIT" value="Add user" name="adduser"></td>
                <td></td>
                <td>Comments:</td>
                <td><input type="Text" name="commentse"></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td><input type="SUBMIT" value="Edit user" name="edituser"></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </Table>
    </form>

</body>
</html>
