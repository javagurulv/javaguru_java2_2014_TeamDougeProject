<%--
  Created by IntelliJ IDEA.
  User: Radchuk
  Date: 11/14/2014
  Time: 12:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%=(String)request.getAttribute("model")%>
<br>

<h2>Add User</h2>
<form method="POST" action="adduser">
    <table>
        <tr>
            <td>
                User Type:
            </td>
            <td>
                <select name="user_type">
                    <option value="0">Admin</option>
                    <option value="1">User</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Login:</td>
            <td><input type="Text" name="login"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="Password" name="passwd"></td>
        </tr>
        <tr>
            <td>Comments:</td>
            <td><input type="Text" name="comments"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="SUBMIT" value="Submit" name="submit"></td>
        </tr>
    </table>
    <br><br>
    <a href="/index"><button type="button">Back to Index</button></a>
</form></body>
</html>
