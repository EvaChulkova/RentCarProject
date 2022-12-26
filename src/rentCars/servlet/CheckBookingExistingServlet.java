package rentCars.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import rentCars.dto.BookingDto;
import rentCars.service.SeeBookingService;
import rentCars.util.JSPHelper;

import java.io.IOException;

import static rentCars.util.UrlPath.CHECK_BOOKING_EXISTING;

@WebServlet(CHECK_BOOKING_EXISTING)
public class CheckBookingExistingServlet extends HttpServlet {
    private final SeeBookingService seeBookingService = SeeBookingService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer bookingId = Integer.valueOf(req.getParameter("bookingId"));

        seeBookingService.findBookingById(bookingId).ifPresentOrElse(bookingDto -> {
            forwardBookingDto(req, resp, bookingDto);
        }, () -> {
            sendError(resp);
        } );
    }

    @SneakyThrows
    private void sendError(HttpServletResponse resp) {
        resp.setStatus(400);
        resp.sendError(400, "Booking does not exist");
    }

    @SneakyThrows
    private void forwardBookingDto(HttpServletRequest req, HttpServletResponse resp, BookingDto bookingDto) {
        req.setAttribute("booking", bookingDto);
        req.getRequestDispatcher(JSPHelper.getPath("checkBooking"))
                .forward(req, resp);
    }
}
