package rentCars.service;

import lombok.NoArgsConstructor;
import rentCars.dao.CarDao;
import rentCars.dao.ClientDao;
import rentCars.dao.UserDao;
import rentCars.dto.BookingDto;
import rentCars.entity.Car;
import rentCars.entity.Client;
import rentCars.entity.User;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateAdminReportService {
    private static final CreateAdminReportService INSTANCE = new CreateAdminReportService();
    private final BookingService bookingService = BookingService.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CarDao carDao = CarDao.getInstance();
    private final ClientDao clientDao = ClientDao.getInstance();

    public String createAdminReport() {
        List<BookingDto> bookingDtoList = bookingService.findAll();
        StringBuffer adminReport = new StringBuffer();
        for (BookingDto bookingDto : bookingDtoList) {
            User user = userDao.findById(bookingDto.getUserId()).orElseThrow();
            Client client = clientDao.findClientByUserId(bookingDto.getUserId());
            Car car = carDao.findById(bookingDto.getCarId()).orElseThrow();
            String booking = String.format("Booking details: " + "\n" +
                                           "Booking Id: %s,  Booking status: %s, Comment: %s " + "\n"+
                                           "Client details: " + "\n" +
                                           "User Id: %s, Client Id: %s, Login: %s, First name: %s, Last name: %s, Driving Licence No: %s, Validity: %s" + "\n" +
                                           "Car details: " + "\n" +
                                           "Car Id: %s, Brand: %s, Color: %s, Seat amount: %s, Price per day: %s rubles" + "\n" +
                                           "-----------------------\\----------------------" + "\n" + System.lineSeparator(),
                    bookingDto.getId(), bookingDto.getStatus(), bookingDto.getComment(),
                    user.getId(), client.getId(), user.getLogin(), user.getFirstName(), user.getLastName(), client.getLicenceNo(), client.getValidity(),
                    car.getId(), car.getBrand(), car.getColor(), car.getSeatAmount(), car.getPrice());
            adminReport.append(booking);
        }
        return adminReport.toString();
    }

    public static CreateAdminReportService getInstance() {
        return INSTANCE;
    }
}
