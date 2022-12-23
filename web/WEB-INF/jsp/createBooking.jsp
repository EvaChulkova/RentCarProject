<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jane
  Date: 23.12.2022
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create booking</title>
</head>
<body>
<h1>Create booking</h1>
<form action="${pageContext.request.contextPath}/create_booking" method="post">
    <label for="car">Car:
        <select var="car" id="car">
            <c:forEach var="car" items="${requestScope.availableCars}">
                <option value="${car.id}">${car.id}</option>
            </c:forEach>
        </select><br>
    </label>
    <label for="rentalStartId"> Start time of rent:
        <input type="datetime-local" name="rentalStart" id="rentalStartId">
    </label><br>
    <label for="rentalFinishId"> End time of rent:
        <input type="datetime-local" name="rentalFinish" id="rentalFinishId">
    </label><br>
    <button type="submit">Send</button>

</form>

</body>
</html>
