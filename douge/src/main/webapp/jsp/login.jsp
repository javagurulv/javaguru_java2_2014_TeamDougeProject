<%--
  Created by IntelliJ IDEA.
  User: Radchuk
  Date: 11/14/2014
  Time: 12:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    String errorMessage = null;

    if (request.getAttribute("model") != null) {

        Integer errorType = (Integer) request.getAttribute("model");

        if (errorType == 0) { //Login Successfull
            Long userType = (Long) session.getAttribute("userType");
            if (userType == 0){
                response.sendRedirect("/users");
            }
            else {
                response.sendRedirect("/home");
            }
        } else if (errorType == 1) { //Login or password incorrect
            errorMessage = "Login and/or password incorrect!";
        } else if (errorType == 2) { //Login or password is empty;
            errorMessage = "Name and/or password can't be empty!";
        }
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
    if (errorMessage != null) {
        out.print("<font color=\"red\">" + errorMessage + "</font><br>");
    }
%>

<h2>Sign In</h2>
<form method="POST" action="login">
    <table>
        <tr>
            <td>Username:</td>
            <td><input type="text" name="login"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="passwd"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="SUBMIT" value="Submit" name="submit"></td>
        </tr>
    </table>
    <br><br>
    <a href="/index"><button type="button">Back to Index</button></a>
</form>
</body>
</html>