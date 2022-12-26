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

import static rentCars.util.UrlPath.CHECK_BOOKING;

@WebServlet(CHECK_BOOKING)
public class CheckBookingServlet extends HttpServlet {
    SeeBookingService seeBookingService = SeeBookingService.getInstance();
    BookingService bookingService = BookingService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer bookingId = Integer.valueOf(req.getParameter("bookingId"));

        seeBookingService.findBookingById(bookingId).ifPresentOrElse(bookingDto -> {
            forwardAlreadyCheckedBooking(req, resp, bookingDto);
        }, () -> {
            sendError(resp);
        } );
    }

    @SneakyThrows
    private void sendError(HttpServletResponse resp) {
        resp.setStatus(400);
        resp.sendError(400, "No orders");
    }

    @SneakyThrows
    private void forwardAlreadyCheckedBooking(HttpServletRequest req, HttpServletResponse resp, BookingDto bookingDto) {
        bookingService.checkBooking(bookingDto);
        req.setAttribute("bookings", bookingService.findAll());
        req.getRequestDispatcher(JSPHelper.getPath("bookings"))
                .forward(req, resp);
    }
}
