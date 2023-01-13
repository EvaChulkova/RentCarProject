package rentCars.util;

import lombok.experimental.UtilityClass;

import java.nio.file.Path;

@UtilityClass
public class UrlPath {
    public static final String LOGIN = "/login";
    public static final String REGISTRATION = "/registration";
    public static final String IMAGES = "/images";
    public static final String LOCALE = "/locale";
    public static final String COOKIES = "/cookies";
    public static final String LOGOUT = "/logout";
    public static final String BOOKINGS = "/bookings";
    public static final String CARS = "/cars";
    public static final String CLIENTS = "/clients";
    public static final String ADD_CAR = "/add_new_car";
    public static final String ADD_CLIENT_INFO = "/add_client_info";
    public static final String CLIENT_BOOKINGS = "/client_bookings";
    public static final String CREATE_BOOKING ="/create_booking";
    public static final String CHECK_BOOKING_EXISTING ="/check_booking_existing";
    public static final String AVAILABLE_CARS = "/available_cars";
    public static final String CHECK_BOOKING = "/check_booking";
    public static final String CANCEL_BOOKING = "/cancel_booking";
    public static final String PAY_BOOKING = "/pay_booking";
    public static final String COMPLETE_BOOKING = "/complete_booking";
    public static final String SEND_CANCEL_MESSAGE = "/send_cancel_message";
    public static final String SEE_INFO_ABOUT_BOOKING = "/see_info_about_booking";
    public static final String PERSONAL_CLIENT_INFO = "/personal_client_info";
    public static final String DOWNLOAD_ADMIN_REPORT = "/download_admin_report";
    public static final String DOWNLOAD_CLIENT_REPORT = "/download_client_report";
    public static final Path ADMIN_REPORT_FULL_PATH = Path.of("home","jane", "DMDev_HW", "jdbc-starter", "resources", "admin_report.txt");
    public static final Path CLIENT_REPORT_FULL_PATH = Path.of("home","jane", "DMDev_HW", "jdbc-starter", "resources", "client_report.txt");

}
