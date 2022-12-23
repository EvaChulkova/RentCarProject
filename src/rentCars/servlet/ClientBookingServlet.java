package rentCars.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rentCars.dto.UserDto;
import rentCars.service.BookingService;
import rentCars.util.JSPHelper;

import java.io.IOException;

import static rentCars.util.UrlPath.CLIENT_BOOKINGS;

@WebServlet(CLIENT_BOOKINGS)
public class ClientBookingServlet extends HttpServlet {
    private final BookingService bookingService = BookingService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = (UserDto) req.getSession().getAttribute("user");
        var bookingByUserId = bookingService.findBookingsByUserId(user.getId());

        req.setAttribute("client_bookings", bookingByUserId);

        req.getRequestDispatcher(JSPHelper.getPath("clientBookings"))
                .forward(req, resp);

    }
}
