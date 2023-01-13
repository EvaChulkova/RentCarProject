package rentCars.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import rentCars.dto.BookingDto;
import rentCars.service.BookingService;
import rentCars.service.SeeBookingService;
import rentCars.util.JSPHelper;

import java.io.IOException;

import static rentCars.util.UrlPath.COMPLETE_BOOKING;

@WebServlet(COMPLETE_BOOKING)
public class CompleteBookingServlet extends HttpServlet {
    private final SeeBookingService seeBookingService = SeeBookingService.getInstance();
    private final BookingService bookingService = BookingService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer bookingId = Integer.valueOf(req.getParameter("bookingId"));

        seeBookingService.findBookingById(bookingId).ifPresentOrElse(
                bookingDto -> {
                    completeBooking(req, resp, bookingDto);
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
    private void completeBooking(HttpServletRequest req, HttpServletResponse resp, BookingDto bookingDto) {
        bookingService.completeBooking(bookingDto);
        req.setAttribute("bookings", bookingService.findAll());

        req.getRequestDispatcher(JSPHelper.getPath("bookings"))
                .forward(req, resp);
    }
}
