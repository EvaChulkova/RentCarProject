package rentCars.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rentCars.service.CarService;
import rentCars.util.JSPHelper;

import java.io.IOException;

@WebServlet("/cars")
public class CarServlet extends HttpServlet {
    private final CarService carService = CarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cars", carService.findAll());

        req.getRequestDispatcher(JSPHelper.getPath("cars"))
                .forward(req, resp);

        /*resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (PrintWriter printWriter = resp.getWriter()) {
            printWriter.write("<h1>Автомобили:</h1>");
            printWriter.write("<ul>");
            carService.findAll().forEach(carDto -> {
                printWriter.write("""
                        <li>
                        %d - %s
                        </li>
                        """.formatted(carDto.getId(), carDto.getDescription()));
            });
            printWriter.write("</ul>");
        }*/
    }

    /*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateCarDto createCarDto = CreateCarDto.builder()
                .brand(req.getParameter("brand"))
                .color(req.getParameter("color"))
                .seatAmount(req.getParameter("seatAmount"))
                .price(req.getParameter("price"))
                .status(req.getParameter("status"))
                .image(req.getPart("image"))
                .build();

        carService.create(createCarDto);

    }*/
}
