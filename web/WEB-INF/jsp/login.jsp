<%--
  Created by IntelliJ IDEA.
  User: jane
  Date: 20.12.2022
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1><fmt:message key="page.login.loginForm" /></h1>
<form action="${pageContext.request.contextPath}/login" method="post">

    <label for="loginId"><fmt:message key="page.login.login" />:
        <input type="text" name="login" id="loginId" placeholder="email@gmail.com" value="${param.login}" required>
    </label><br>

    <label for="passwordId"> <fmt:message key="page.login.password" />:
        <input type="password" name="password" id="passwordId" required>
    </label><br>

    <button type="submit"> <fmt:message key="page.login.submit.button" /></button><br><br>

    <a href="${pageContext.request.contextPath}/registration">
        <button type="button"> <fmt:message key="page.login.registration.button" /></button>
    </a>

    <c:if test="${param.error != null}">
        <div style="color: red">
            <span><fmt:message key="page.login.error" /></span>
        </div>
    </c:if>
</form>

</body>
</html>
