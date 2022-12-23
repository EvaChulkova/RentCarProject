package rentCars.servlet.CreateServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rentCars.dto.CarDto;
import rentCars.dto.CreateDto.CreateBookingDto;
import rentCars.dto.UserDto;
import rentCars.entity.enums.BookingStatusEnum;
import rentCars.service.BookingService;
import rentCars.service.CarService;
import rentCars.util.JSPHelper;

import java.io.IOException;
import java.util.List;

import static rentCars.util.UrlPath.AVAILABLE_CARS;
import static rentCars.util.UrlPath.CREATE_BOOKING;

@WebServlet(CREATE_BOOKING)
public class CreateBookingServlet extends HttpServlet {
    private final BookingService bookingService = BookingService.getInstance();
    private final CarService carService = CarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("availableCars", carService.findAvailableCars());

        req.getRequestDispatcher(JSPHelper.getPath("my_booking"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CarDto> availableCars = carService.findAvailableCars();
        var user = (UserDto) req.getSession().getAttribute("user");
        var bookingDto = CreateBookingDto.builder()
                .userId(user.getId())
                .carId(carService.findFromAvailableById(availableCars, req.getParameter("car")))
                .rentalStart(req.getParameter("rentalStart"))
                .rentalFinish(req.getParameter("rentalFinish"))
                .status(BookingStatusEnum.IN_PROGRESS)
                .comment("In progress. Please, wait...")
                .build();

        bookingService.create(bookingDto);
        resp.sendRedirect(AVAILABLE_CARS);

    }
}
