package rentCars.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import rentCars.dto.UserDto;
import rentCars.entity.enums.RoleEnum;
import rentCars.service.ClientService;
import rentCars.service.UserService;
import rentCars.util.JSPHelper;

import java.io.IOException;

import static rentCars.util.UrlPath.ADD_CLIENT_INFO;
import static rentCars.util.UrlPath.AVAILABLE_CARS;
import static rentCars.util.UrlPath.CARS;
import static rentCars.util.UrlPath.LOGIN;

@WebServlet(LOGIN)
public class LoginServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();
    private final ClientService clientService = ClientService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSPHelper.getPath("login"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.login(req.getParameter("login"), req.getParameter("password"))
                .ifPresentOrElse(
                        user -> onLoginSuccess(user, req, resp),
                        () -> onLoginFail(req, resp)
                );
    }

    @SneakyThrows
    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect(LOGIN + "?error&login=" + req.getParameter("login"));
    }

    @SneakyThrows
    private void onLoginSuccess(UserDto user, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user", user);
        if (user.getRole().equals(RoleEnum.CLIENT)) {
            if (clientService.findClientID(user.getId()).isPresent()) {
                resp.sendRedirect(AVAILABLE_CARS);
            } else {
                resp.sendRedirect(ADD_CLIENT_INFO);
            }
        } else {
            resp.sendRedirect(CARS);
        }
    }
}
