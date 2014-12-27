<%--
  Created by IntelliJ IDEA.
  User: Juris
  Date: 09.11.2014
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome!</title>
    <link rel="stylesheet" type="text/css" href="/style/style.css">
</head>
<body >
<hr>
<div id="header">
    <h2>Team Douge Project</h2>
</div>
<hr>
<form method="POST" action="login">
    <table align="right">
        <tr>
            <td><b>Sign In:</b></td>
            <td>Username:</td>
            <td><input type="text" name="login"></td>
            <td>Password:</td>
            <td><input type="password" name="passwd"></td>
            <td><input type="SUBMIT" value="Submit" name="submit"></td>
        </tr>
    </table>
    <!--&nbsp&nbsp&nbsp&nbsp&nbsp
    <b>Sign In:</b> Username: <input type="text" name="login">
    Password: <input type="password" name="passwd">
    <input type="SUBMIT" value="Submit" name="submit"> -->
</form>
<br>

<hr>

<table align="center" width="90%" height="70%">
    <tr>
        <!--<td align="center"><a href="/home"><button type="button">Home</button></a></td>
        <td align="center"><a href="/login"><button type="button">Sign in</button></a></td>
        <!-- <td align="center"><a href="/users"><button type="button">Add user</button></a></td>
        <td align="center"><a href="/films"><button type="button">Film list</button></a></td>
        <td align="center"><a href="/actors"><button type="button">Actor list</button></a></td> -->
    </tr>
</table>

<div id="footer">
    Copyright © Team Douge Project
</div>
</body>
</html>
