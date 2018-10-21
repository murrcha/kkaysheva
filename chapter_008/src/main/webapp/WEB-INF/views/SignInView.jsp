<%--
  Created by IntelliJ IDEA.
  User: kkaysheva
  Date: 2018-10-18
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Sign In</title>
</head>
<body>
    <form action="${pageContext.servletContext.contextPath}/signin" method="post">
        <table>
            <tr>
                <th>Login:</th>
                <td><input type="text" name="login"></td>
            </tr>
            <tr>
                <th>Password:</th>
                <td><input type="password" name="password"></td>
            </tr>
        </table>
        <input type="submit" value="SIGN IN">
    </form>
<c:if test="${error != ''}">
    <div style="background-color: #ff795e">
        <c:out value="${error}"/>
    </div>
</c:if>
</body>
</html>
