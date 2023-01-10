package rentCars.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rentCars.service.BookingService;
import rentCars.util.JSPHelper;

import java.io.IOException;

import static rentCars.util.UrlPath.BOOKINGS;

@WebServlet(BOOKINGS)
public class BookingServlet extends HttpServlet {
    private final BookingService bookingService = BookingService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("bookings", bookingService.findAll());

        req.getRequestDispatcher(JSPHelper.getPath("bookings"))
                .forward(req, resp);

    }
}
