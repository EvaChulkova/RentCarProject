package rentCars.service;

import lombok.NoArgsConstructor;
import rentCars.dao.ClientDao;
import rentCars.dao.UserDao;
import rentCars.dto.BookingDto;
import rentCars.entity.User;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DownloadAdminService {
    private static final DownloadAdminService INSTANCE = new DownloadAdminService();
    private final BookingService bookingService = BookingService.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final ClientDao clientDao = ClientDao.getInstance();

    public String createAdminReport() {
        List<BookingDto> bookingDtoList = bookingService.findAll();
        StringBuffer adminReport = new StringBuffer();
        for (BookingDto bookingDto : bookingDtoList) {
            User user = userDao.findById(bookingDto.getUserId()).orElseThrow();
            String booking = String.format("Booking Id: %s, User: First name: %s Last name: %s Car Id:%s Booking status: %s;" + System.lineSeparator(),
                    bookingDto.getId(), user.getFirstName(), user.getLastName(), bookingDto.getCarId(), bookingDto.getStatus());
            adminReport.append(booking);
        }
        return adminReport.toString();
    }

    public static DownloadAdminService getInstance() {
        return INSTANCE;
    }
}
