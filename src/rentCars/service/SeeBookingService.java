package rentCars.service;

import rentCars.dao.BookingDao;
import rentCars.dto.BookingDto;

import java.util.Optional;

public class SeeBookingService {
    private static final SeeBookingService INSTANCE = new SeeBookingService();
    private final BookingDao bookingDao = BookingDao.getInstance();

    public Optional<BookingDto> findBookingById(Integer id) {
        return bookingDao.findById(id.longValue())
                .map(booking -> BookingDto.builder()
                        .id(booking.getId())
                        .userId(booking.getUserId())
                        .carId(booking.getCarId())
                        .rentalStart(booking.getRentalStart())
                        .rentalFinish(booking.getRentalFinish())
                        .status(booking.getStatus())
                        .comment(booking.getComment())
                        .build());
    }

    public static SeeBookingService getInstance() {
        return INSTANCE;
    }
}
