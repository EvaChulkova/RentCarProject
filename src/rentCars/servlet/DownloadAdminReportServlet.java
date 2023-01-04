package rentCars.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rentCars.service.CreateAdminReportService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static rentCars.util.UrlPath.ADMIN_REPORT_FULL_PATH;
import static rentCars.util.UrlPath.DOWNLOAD_ADMIN_REPORT;

@WebServlet(DOWNLOAD_ADMIN_REPORT)
public class DownloadAdminReportServlet extends HttpServlet {
    private final CreateAdminReportService createAdminReportService = CreateAdminReportService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Disposition", "attachment; filename=\"admin_report.txt\"");
        resp.setContentType("text/plain");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        Files.writeString(ADMIN_REPORT_FULL_PATH, createAdminReportService.createAdminReport());

        try (PrintWriter writer = resp.getWriter()) {
            Files.createDirectories(ADMIN_REPORT_FULL_PATH.getParent());
            writer.write(Files.readString(ADMIN_REPORT_FULL_PATH));
        }
    }
}
