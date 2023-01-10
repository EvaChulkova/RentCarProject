<%--
  Created by IntelliJ IDEA.
  User: jane
  Date: 25.12.2022
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Check booking</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1><fmt:message key="page.checkBooking.checkNewBooking" /></h1>
<ul>
    Booking identification number: ${requestScope.booking.id} - Client (user) ID:${requestScope.booking.userId} - Car ID: ${requestScope.booking.carId} <br><br>

    Rental period: <br>
    From: ${requestScope.booking.rentalStart}  To: ${requestScope.booking.rentalFinish} <br><br>

    Status:
    ${requestScope.booking.status}, ${requestScope.booking.comment}

        <h3>Info about car:</h3>
        Car identification number: ${requestScope.carById.id} <br>
        Brand: ${requestScope.carById.brand} <br>
        Color: ${requestScope.carById.color} <br>
        Price per day: ${requestScope.carById.price} rubles
</ul>

<form action="${pageContext.request.contextPath}/check_booking" method="get">
    <button type="submit" name="bookingId" value="${booking.id}"> <fmt:message key="page.checkBooking.button.checkBooking" /></button>
</form>
<form action="${pageContext.request.contextPath}/bookings" method="get">
    <button type="submit"><fmt:message key="page.checkBooking.button.back" /></button>
</form>
</body>
</html>
