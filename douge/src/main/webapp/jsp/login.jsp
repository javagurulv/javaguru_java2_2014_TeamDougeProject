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
            response.sendRedirect("/home");
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
<jsp:include page="templates/login_form.html" ></jsp:include>
</body>
</html>