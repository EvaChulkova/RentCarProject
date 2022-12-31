package rentCars.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static rentCars.util.UrlPath.ADMIN_REPORT_FULL;
import static rentCars.util.UrlPath.DOWNLOAD_ADMIN;

@WebServlet(DOWNLOAD_ADMIN)
public class DownloadAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Disposition", "attachment; filename=\"admin_report.txt\"");
        resp.setContentType("text/plain");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (PrintWriter writer = resp.getWriter()) {
            Files.createDirectories(ADMIN_REPORT_FULL.getParent());
            writer.write(Files.readString(ADMIN_REPORT_FULL));
        }

        /*try (var outputStream = resp.getOutputStream();
             var stream = DownloadAdminServlet.class.getClassLoader().getResourceAsStream("admin_report.txt")) {
            outputStream.write(stream.readAllBytes());
        }*/
    }
}
