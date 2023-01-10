package rentCars.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rentCars.service.CarService;
import rentCars.util.JSPHelper;

import java.io.IOException;

import static rentCars.util.UrlPath.CARS;

@WebServlet(CARS)
public class CarServlet extends HttpServlet {
    private final CarService carService = CarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cars", carService.findAll());

        req.getRequestDispatcher(JSPHelper.getPath("cars"))
                .forward(req, resp);
    }
}
