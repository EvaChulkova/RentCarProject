package rentCars.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rentCars.dto.CreateDto.CreateUserDto;
import rentCars.entity.enums.RoleEnum;
import rentCars.exception.ValidationException;
import rentCars.service.UserService;
import rentCars.util.JSPHelper;

import java.io.IOException;

import static rentCars.util.UrlPath.LOGIN;
import static rentCars.util.UrlPath.REGISTRATION;

@WebServlet(REGISTRATION)
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", RoleEnum.values());

        req.getRequestDispatcher(JSPHelper.getPath("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var createUserDto = CreateUserDto.builder()
                .firstName(req.getParameter("firstName"))
                .lastName(req.getParameter("lastName"))
                .login(req.getParameter("login"))
                .password(req.getParameter("password"))
                .role(req.getParameter("role"))
                .build();

        try {
            String login = createUserDto.getLogin();
            String regexLogin = "[a-zA-Z0-9]\\w*@\\w{3,}\\.(com|ru)";
            if (login.matches(regexLogin)) {
                userService.create(createUserDto);
                resp.sendRedirect(LOGIN);
            } else {
                resp.sendRedirect(REGISTRATION + "?error&login=" + req.getParameter("login"));
            }


        } catch (ValidationException validationException) {
            req.setAttribute("rent_errors", validationException.getErrors());
            doGet(req, resp);
        }
    }
}
