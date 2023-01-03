package rentCars.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rentCars.service.CreateAdminReportService;

import java.io.IOException;
import java.nio.file.Files;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static rentCars.util.UrlPath.ADMIN_REPORT_FULL_PATH;
import static rentCars.util.UrlPath.CREATE_ADMIN_REPORT;

@WebServlet(CREATE_ADMIN_REPORT)
public class CreateAdminReportServlet extends HttpServlet {
    private final CreateAdminReportService createAdminReportService = CreateAdminReportService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Files.writeString(ADMIN_REPORT_FULL_PATH, createAdminReportService.createAdminReport(), CREATE, TRUNCATE_EXISTING);
    }
}
