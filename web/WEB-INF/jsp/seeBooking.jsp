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
    <title>My booking</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1><fmt:message key="page.seeBooking.seeInfo" /></h1>
<ul>
    Booking identification number: ${requestScope.booking.id}<br><br> <%--- ${requestScope.booking.userId}--%>

    Car identification number: ${requestScope.booking.carId} <br><br>

    Rental period: <br>
    From: ${requestScope.booking.rentalStart} - To: ${requestScope.booking.rentalFinish} <br><br>

    Status: <br>
    ${requestScope.booking.status}, ${requestScope.booking.comment}
</ul>

<form action="${pageContext.request.contextPath}/client_bookings" method="get">
    <button type="submit"><fmt:message key="page.seeBooking.button.back" /></button>
</form>
</body>
</html>
