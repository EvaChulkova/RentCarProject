<%--
  Created by IntelliJ IDEA.
  User: jane
  Date: 12.01.2023
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Personal account</title>
</head>
<body>
<div>
    <c:if test="${not empty sessionScope.user}">
        <div id="logout">
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit">Logout</button>
            </form>
        </div>
    </c:if>
</div>

<h1>Personal information</h1>
<ul>
    <b>Client ID: </b>${requestScope.personal_client_information.id} <br>
    <b>Login: </b>${requestScope.user_personal_information.login} <br>
    <b>First name: </b> ${requestScope.user_personal_information.firstName} <br>
    <b>Last name: </b>${requestScope.user_personal_information.lastName} <br>
    <b>Age: </b>${requestScope.personal_client_information.age} <br>
    <b>Driving licence No: </b>${requestScope.personal_client_information.licenceNo} <br>
    <b>Driving licence validity: </b>${requestScope.personal_client_information.validity} <br>
</ul>

<form action="${pageContext.request.contextPath}/download_client_report" method="get">
    <button type="submit">Download report about my bookings</button>
</form>

<form action="${pageContext.request.contextPath}/available_cars" method="get">
    <button type="submit"> Go to main page </button>
</form>

</body>
</html>
