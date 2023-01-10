package rentCars.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import rentCars.dto.BookingDto;
import rentCars.service.BookingService;
import rentCars.service.CarService;
import rentCars.service.SeeBookingService;
import rentCars.util.JSPHelper;

import java.io.IOException;

import static rentCars.util.UrlPath.SEE_INFO_ABOUT_BOOKING;

@WebServlet(SEE_INFO_ABOUT_BOOKING)
public class SeeBookingServlet extends HttpServlet {
    private final SeeBookingService seeBookingService = SeeBookingService.getInstance();
    private final CarService carService = CarService.getInstance();
    private final BookingService bookingService = BookingService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer bookingId = Integer.valueOf(req.getParameter("bookingId"));

        showInfoAboutCarFromOrder(req, bookingId);

        seeBookingService.findBookingById(bookingId).ifPresentOrElse(bookingDto -> {
            forwardBookingDto(req, resp, bookingDto);
        }, () -> {
            sendError(resp);
        });
    }

    @SneakyThrows
    private void sendError(HttpServletResponse resp) {
        resp.setStatus(400);
        resp.sendError(400, "Order does not exist :(");
    }

    @SneakyThrows
    private void forwardBookingDto(HttpServletRequest req, HttpServletResponse resp, BookingDto bookingDto) {
        req.setAttribute("booking", bookingDto);
        req.getRequestDispatcher(JSPHelper.getPath("seeBooking"))
                .forward(req, resp);
    }

    private void showInfoAboutCarFromOrder(HttpServletRequest req, Integer bookingId) {
        var carIdFromBooking = bookingService.findCarIdByBookingId(bookingId);
        var carFromBookingById = carService.findNotOptionalCarById(carIdFromBooking);
        req.setAttribute("carById", carFromBookingById);
    }
}
