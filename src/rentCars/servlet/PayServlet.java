package rentCars.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import rentCars.dto.BookingDto;
import rentCars.dto.UserDto;
import rentCars.service.BookingService;
import rentCars.service.SeeBookingService;
import rentCars.util.JSPHelper;

import java.io.IOException;

import static rentCars.util.UrlPath.PAY_BOOKING;

@WebServlet(PAY_BOOKING)
public class PayServlet extends HttpServlet {
    private final SeeBookingService seeBookingService = SeeBookingService.getInstance();
    private final BookingService bookingService = BookingService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer bookingId = Integer.valueOf(req.getParameter("bookingId"));

        seeBookingService.findBookingById(bookingId).ifPresentOrElse(
                bookingDto -> {
                    payBooking(req, resp, bookingDto);
                }, () -> {
                    sendError(resp);
                }
        );
    }

    @SneakyThrows
    private void sendError(HttpServletResponse resp) {
        resp.setStatus(400);
        resp.sendError(400, "No orders");
    }

    @SneakyThrows
    private void payBooking(HttpServletRequest req, HttpServletResponse resp, BookingDto bookingDto) {
        bookingService.payBooking(bookingDto);

        var user = (UserDto) req.getSession().getAttribute("user");
        var bookingByUserId = bookingService.findBookingsByUserId(user.getId());
        req.setAttribute("client_bookings", bookingByUserId);

        req.getRequestDispatcher(JSPHelper.getPath("clientBookings"))
                .forward(req, resp);
    }
}
