<%--
  Created by IntelliJ IDEA.
  User: jane
  Date: 18.12.2022
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<%@ include file="header.jsp" %>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="firstNameId"><fmt:message key="page.registration.firstName" />:
        <input type="text" name="firstName" id="firstNameId">
    </label><br>
    <label for="lastNameId"><fmt:message key="page.registration.lastName" />:
        <input type="text" name="lastName" id="lastNameId">
    </label><br>
    <label for="loginId"><fmt:message key="page.registration.login" />:
        <input type="text" name="login" id="loginId">
    </label><br>
    <label for="passwordId"><fmt:message key="page.registration.password" />:
        <input type="password" name="password" id="passwordId">
    </label><br>
    <label for="roleId"><fmt:message key="page.registration.role" />:
        <select name="role" id="roleId">
            <c:forEach var="role" items="${requestScope.roles}">
                <option value="${role}">${role}</option>
            </c:forEach>
        </select>
    </label><br>

    <button type="submit"><fmt:message key="page.registration.button.send" /></button>
    <c:if test="${not empty requestScope.rentErrors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.rentErrors}">
                <span>${error.message}</span>
            </c:forEach>
        </div>
    </c:if>

</form>
</body>
</html>
