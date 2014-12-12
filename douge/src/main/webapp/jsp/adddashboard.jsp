<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12-Dec-14
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    //allow access only if session exists
    String sessionLogin = null;
    if (session.getAttribute("sessionLogin") != null) {
        sessionLogin = (String) session.getAttribute("sessionLogin");
    }
    else {
        response.sendRedirect("/login");
    }

    Integer errorCode = (Integer) request.getAttribute("model");
%>

<html>
<head>
    <title>Add Dashboard</title>
    <script>
        window.onunload = refreshParent;
        function refreshParent() {
            window.opener.location.reload();
        }
    </script>
</head>
<body>
    <h2>Add Dashboard</h2>
    <form method="POST" action="adddashboard">
        <table>
            <tr>
                <td colspan="2">&nbsp;
                    <%
                        if (errorCode == 0) {
                            out.println("Dashboard successfully added!");
                        } if (errorCode == 1) {
                            out.println("<font color=\"red\">Name can't be empty</font>");
                        }
                    %>
                </td>
            </tr>
            <tr>
                <td>Dashboard name:</td>
                <td>
                    <input type="Text" name="dashboard_name">
                </td>
            </tr>
            <tr>
                <td></td>
                <td><input type="SUBMIT" value="Submit" name="submit"></td>
            </tr>
        </table>
    </form>
</body>
</html>
