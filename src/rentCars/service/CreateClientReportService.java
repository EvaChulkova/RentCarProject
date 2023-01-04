package rentCars.service;

import lombok.NoArgsConstructor;
import rentCars.dao.ClientDao;
import rentCars.dao.UserDao;
import rentCars.dto.BookingDto;

import java.util.List;

@NoArgsConstructor
public class CreateClientReportService {
    private static final CreateClientReportService INSTANCE = new CreateClientReportService();
    private final BookingService bookingService = BookingService.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final ClientDao clientDao = ClientDao.getInstance();

    public String createClientReport() {
        List<BookingDto> allBookingDtoList = bookingService.findAll();
        StringBuilder clientReport = new StringBuilder();
        for (BookingDto bookingDto : allBookingDtoList) {
            clientDao.findClientIdByUserId(bookingDto.getUserId()).orElseThrow();
            String clientBookings = String.format("Booking Id: %s, Car id: %s, Start: %s, Finish: %s, Status: %s, Comment: %s" + System.lineSeparator(),
                    bookingDto.getId(), bookingDto.getCarId(), bookingDto.getRentalStart(), bookingDto.getRentalFinish(), bookingDto.getStatus(), bookingDto.getComment());
            clientReport.append(clientBookings);
        }
        return clientReport.toString();
    }

    public static CreateClientReportService getInstance() {
        return INSTANCE;
    }
}
