<%--
  Created by IntelliJ IDEA.
  User: jane
  Date: 24.12.2022
  Time: 00:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>My bookings</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1><fmt:message key="page.clientBookings.allMyBookings" /></h1>
<ul>
    <c:forEach var="client_booking" items="${requestScope.client_bookings}">
        <li>
            <a href="${pageContext.request.contextPath}/see_info_about_booking?bookingId=${client_booking.id}">
                <b>Car ID: </b>${client_booking.carId} \---\
                <b>Rental period:</b>
                From: ${client_booking.rentalStart} - To: ${client_booking.rentalFinish} <br>

                <b>Booking status: </b>${client_booking.status}
            </a>
        </li>
    </c:forEach>
</ul>

<form action="${pageContext.request.contextPath}/available_cars" method="get">
    <button type="submit"> <fmt:message key="page.clientBookings.button.back" /></button>
</form>

</body>
</html>
