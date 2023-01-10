<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jane
  Date: 18.12.2022
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Bookings</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1><fmt:message key="page.bookings.allBookings" /></h1>
<ul>
    <c:forEach var="booking" items="${requestScope.bookings}">
        <li>
            <a href="${pageContext.request.contextPath}/check_booking_existing?bookingId=${booking.id}">
                    User ID: ${booking.userId}  Car ID: ${booking.carId}  Rental start: ${booking.rentalStart} - Rental finish: ${booking.rentalFinish} <br>  Status: ${booking.status}, ${booking.comment} <br><br>
            </a>

        </li>
    </c:forEach>
</ul>

<form action="${pageContext.request.contextPath}/cars" method="get">
    <button type="submit"><fmt:message key="page.bookings.button.back" /></button>
</form>

</body>
</html>
