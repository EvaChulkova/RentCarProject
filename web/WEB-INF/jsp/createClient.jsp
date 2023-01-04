<%--
  Created by IntelliJ IDEA.
  User: jane
  Date: 19.12.2022
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title><fmt:message key="page.createClient.title" /></title>
</head>
<body>
<%@ include file="header.jsp" %>
<%--<h1>Add info about you (client):</h1>--%>
<h1><fmt:message key="page.createClient.addInfo" /></h1>
<form action="${pageContext.request.contextPath}/add_client_info" method="post">
    <label for="ageId"><fmt:message key="page.createClient.age" />:
        <input type="text" name="age" id="ageId" required>
    </label><br>
    <label for="licenceNoId"><fmt:message key="page.createClient.licenceNo" />:
        <input type="text" name="licenceNo" id="licenceNoId" required>
    </label><br>
    <label for="validityId"><fmt:message key="page.createClient.validity" />:
        <input type="date" name="validity" id="validityId" required>
    </label><br>
    <button type="submit"><fmt:message key="page.createClient.button.send" /></button>
</form>
</body>
</html>
