package rentCars.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rentCars.dto.UserDto;
import rentCars.entity.enums.RoleEnum;

import java.io.IOException;
import java.util.Set;

import static rentCars.util.UrlPath.*;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {
    private static final Set<String> PUBLIC_PATH = Set.of(LOGIN, LOGOUT, REGISTRATION, LOCALE);
    private static final Set<String> CLIENT_PATH = Set.of(IMAGES, ADD_CLIENT_INFO, CLIENT_BOOKINGS, CREATE_BOOKING, AVAILABLE_CARS, SEE_INFO_ABOUT_BOOKING, DOWNLOAD_CLIENT_REPORT);
    private static final Set<String> ADMINISTRATOR_PATH = Set.of(IMAGES, CLIENTS, ADD_CAR, CARS, BOOKINGS, CHECK_BOOKING_EXISTING, DOWNLOAD_ADMIN_REPORT, CHECK_BOOKING);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var uri = ((HttpServletRequest) servletRequest).getRequestURI();

        if (isAdministratorPath(uri) && isAdministratorLoggedIn(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (isClientPath(uri) && isClientLoggedIn(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (isPublicPath(uri) || isUserLoggedIn(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            var previousPage = ((HttpServletRequest) servletRequest).getHeader("referer");
            ((HttpServletResponse) servletResponse).sendRedirect(previousPage != null ? previousPage : LOGIN);
        }
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        var user = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null;
    }

    private boolean isAdministratorLoggedIn(ServletRequest servletRequest) {
        var user = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user.getRole() == RoleEnum.ADMIN;
    }

    private boolean isClientLoggedIn(ServletRequest servletRequest) {
        var user = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user.getRole() == RoleEnum.CLIENT;
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream().anyMatch(uri::startsWith);
    }

    private boolean isAdministratorPath(String uri) {
        return ADMINISTRATOR_PATH.stream().anyMatch(uri::startsWith);
    }

    private boolean isClientPath(String uri) {
        return CLIENT_PATH.stream().anyMatch(uri::startsWith);
    }
}
