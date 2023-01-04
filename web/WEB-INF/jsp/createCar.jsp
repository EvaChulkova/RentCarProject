<%--
  Created by IntelliJ IDEA.
  User: jane
  Date: 17.12.2022
  Time: 08:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Add new car</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1><fmt:message key="page.createCar.addNewCar" /></h1>

<form action="${pageContext.request.contextPath}/add_new_car" method="post" enctype="multipart/form-data">
    <label for="brandId"> <fmt:message key="page.createCar.brand" />:
        <input type="text" name="brand" id="brandId">
    </label><br>
    <label for="colorId"> <fmt:message key="page.createCar.color" />:
        <select name="color" id="colorId">
            <c:forEach var="color" items="${requestScope.color}">
                <option value="${color}">${color}</option>
            </c:forEach>
        </select>
    </label><br>
    <label for="seatAmountId"> <fmt:message key="page.createCar.seatAmount" />:
        <input type="text" name="seatAmount" id="seatAmountId">
    </label><br>
    <label for="priceId"> <fmt:message key="page.createCar.price" />:
        <input type="text" name="price" id="priceId">
    </label><br>
    <label for="statusId"> <fmt:message key="page.createCar.status" />:
        <select name="status" id="statusId">
            <c:forEach var="status" items="${requestScope.status}">
                <option value="${status}">${status}</option>
            </c:forEach>
        </select>
    </label><br>
    <label for="imageId"><fmt:message key="page.createCar.image" />:
        <input type="file" name="image" id="imageId" required>
    </label><br>

    <button type="submit"><fmt:message key="page.createCar.button.send" /></button>

</form>
<form action="${pageContext.request.contextPath}/cars" method="get">
    <button type="submit"><fmt:message key="page.createCar.button.back" /></button>
</form>
</body>
</html>
