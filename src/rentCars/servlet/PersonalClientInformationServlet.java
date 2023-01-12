package rentCars.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rentCars.dto.UserDto;
import rentCars.service.ClientService;
import rentCars.service.UserService;
import rentCars.util.JSPHelper;

import java.io.IOException;

import static rentCars.util.UrlPath.PERSONAL_CLIENT_INFO;

@WebServlet(PERSONAL_CLIENT_INFO)
public class PersonalClientInformationServlet extends HttpServlet {

    private final ClientService clientService = ClientService.getInstance();
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = (UserDto) req.getSession().getAttribute("user");

        req.setAttribute("personal_client_information", clientService.findClientByUserId(user.getId()));
        req.setAttribute("user_personal_information", userService.findNotOptionalUserById(user.getId()));
        req.getRequestDispatcher(JSPHelper.getPath("personalClientInfo"))
                .forward(req, resp);
    }
}
