<%--
  Created by IntelliJ IDEA.
  User: kkaysheva
  Date: 2018-10-13
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Create user</title>
</head>
<body>
<h2>Create user</h2>
<table>
    <form action="${pageContext.servletContext.contextPath}/create" method="post">
        <tr>
            <th>Login</th>
            <td><input required type="text" name="login" placeholder="login"/></td>
        </tr>
        <tr>
            <th>Name</th>
            <td><input required type="text" name="name" placeholder="name"></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><input required type="text" name="email" placeholder="email@site.com"></td>
        </tr>
        <tr>
            <br/>
            <td colspan="2"><input type="submit" value="CREATE"></td>
        </tr>
    </form>
</table>
</body>
</html>
