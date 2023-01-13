package rentCars.service;

import lombok.NoArgsConstructor;
import rentCars.dao.CarDao;
import rentCars.dao.ClientDao;
import rentCars.dao.UserDao;
import rentCars.dto.BookingDto;
import rentCars.entity.Car;

import java.util.List;

@NoArgsConstructor
public class CreateClientReportService {
    private static final CreateClientReportService INSTANCE = new CreateClientReportService();
    private final BookingService bookingService = BookingService.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final ClientDao clientDao = ClientDao.getInstance();
    private final CarDao carDao = CarDao.getInstance();

    public String createClientReport(Integer userId) {
        List<BookingDto> bookingsByUserId = bookingService.findBookingsByUserId(userId);
        StringBuilder clientReport = new StringBuilder();
        for (BookingDto bookingDto : bookingsByUserId) {
            Car car = carDao.findNotOptionalCar(bookingDto.getCarId());
            String clientBookings = String.format("Booking details: " + "\n" +
                                                  "Booking Id: %s, Car id: %s, Start: %s, Finish: %s, Status: %s, Comment: %s" + "\n" +
                                                  "Car details: " + "\n" +
                                                  "Car id: %s, Brand: %s, Color: %s, Seat amount: %s, Price per day: %s rubles" + "\n" +
                                                  "-----------------------\\----------------------" + "\n" + System.lineSeparator(),
                    bookingDto.getId(), bookingDto.getCarId(), bookingDto.getRentalStart(), bookingDto.getRentalFinish(), bookingDto.getStatus(), bookingDto.getComment(),
                    car.getId(), car.getBrand(), car.getColor(), car.getSeatAmount(), car.getPrice());
            clientReport.append(clientBookings);
        }
        return clientReport.toString();
    }

    public static CreateClientReportService getInstance() {
        return INSTANCE;
    }
}
