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

import static rentCars.util.UrlPath.CLIENT_REPORT_FULL_PATH;
import static rentCars.util.UrlPath.DOWNLOAD_CLIENT;

@WebServlet(DOWNLOAD_CLIENT)
public class DownloadClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Disposition", "attachment; filename=\"client_report.txt\"");
        resp.setContentType("text/plain");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (PrintWriter writer = resp.getWriter()) {
            Files.createDirectories(CLIENT_REPORT_FULL_PATH.getParent());
            writer.write(Files.readString(CLIENT_REPORT_FULL_PATH));
        }

        /*try (var outputStream = resp.getOutputStream();
             var stream = DownloadAdminServlet.class.getClassLoader().getResourceAsStream("admin_report.txt")) {
            outputStream.write(stream.readAllBytes());
        }*/
    }
}
