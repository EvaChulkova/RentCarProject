package rentCars.servlet.CreateServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rentCars.dto.CreateDto.CreateBookingDto;
import rentCars.dto.UserDto;
import rentCars.entity.enums.BookingStatusEnum;
import rentCars.service.BookingService;
import rentCars.service.CarService;
import rentCars.util.JSPHelper;

import java.io.IOException;

import static rentCars.util.UrlPath.AVAILABLE_CARS;
import static rentCars.util.UrlPath.CREATE_BOOKING;

@WebServlet(CREATE_BOOKING)
public class CreateBookingServlet extends HttpServlet {
    private final BookingService bookingService = BookingService.getInstance();
    private final CarService carService = CarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var carId = Integer.valueOf(req.getParameter("carId"));
        req.setAttribute("availableCars", carService.findAvailableCarById(carId));

        req.getRequestDispatcher(JSPHelper.getPath("createBooking"))
                .forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = (UserDto) req.getSession().getAttribute("user");
        var bookingDto = CreateBookingDto.builder()
                .userId(user.getId())
                .carId(Integer.valueOf(req.getParameter("id")))
                .rentalStart(req.getParameter("rentalStart"))
                .rentalFinish(req.getParameter("rentalFinish"))
                .status(BookingStatusEnum.IN_PROGRESS)
                .comment("In progress. Please, wait...")
                .build();

        bookingService.create(bookingDto);
        resp.sendRedirect(AVAILABLE_CARS);

    }
}
