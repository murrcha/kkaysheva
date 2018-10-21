<%--
  Created by IntelliJ IDEA.
  User: kkaysheva
  Date: 2018-10-13
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Update user</title>
</head>
<body>
<h2>Update user</h2>
<table>
    <form action="${pageContext.servletContext.contextPath}/edit" method="post">
        <c:set var="user" value="${user}"/>
        <tr>
            <th>Id</th>
            <td><c:out value="${user.id}"/></td>
            <input type="hidden" name="id" value="${user.id}"/>
        </tr>
        <tr>
            <th>Login</th>
            <td><input required type="text" name="login" value="${user.login}"/></td>
        </tr>
        <tr>
            <th>Name</th>
            <td><input required type="text" name="name" value="${user.name}"></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><input required type="text" name="email" value="${user.email}"></td>
        </tr>
        <tr>
            <th>Password</th>
            <td><input required type="password" name="password" value=""></td>
        </tr>
        <tr>
            <th>Role</th>
            <td>
                <c:if test="${pageContext.session.getAttribute('role') == 1}">
                    <select name="role">
                        <c:choose>
                            <c:when test="${user.role == 1}">
                                <option value="1" selected>***ADMIN***</option>
                                <option value="2">***USER***</option>
                            </c:when>
                            <c:when test="${user.role == 2}">
                                <option value="1">***ADMIN***</option>
                                <option value="2" selected>***USER***</option>
                            </c:when>
                        </c:choose>
                    </select>
                </c:if>
                <c:if test="${pageContext.session.getAttribute('role') != 1}">
                    <c:choose>
                        <c:when test="${user.role == 1}">ADMIN</c:when>
                        <c:when test="${user.role == 2}">USER</c:when>
                    </c:choose>
                    <input type="hidden" name="role" value="${user.role}">
                </c:if>
            </td>
        </tr>
        <tr>
            <br/>
            <td colspan="2"><input type="submit" value="SAVE"></td>
        </tr>
    </form>
</table>
</body>
</html>
