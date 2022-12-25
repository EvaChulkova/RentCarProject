package rentCars.service;

import rentCars.dao.BookingDao;
import rentCars.dao.CarDao;
import rentCars.dto.BookingDto;
import rentCars.dto.CreateDto.CreateBookingDto;
import rentCars.entity.Booking;
import rentCars.mapper.CreateBookingMapper;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class BookingService {
    private static final BookingService INSTANCE = new BookingService();
    private final BookingDao bookingDao = BookingDao.getInstance();
    private final CreateBookingMapper createBookingMapper = CreateBookingMapper.getInstance();
    private final CarDao carDao = CarDao.getInstance();
    private BookingService(){}

    public List<BookingDto> findAll() {
        return bookingDao.findAll().stream()
                .map(booking -> BookingDto.builder()
                        .id(booking.getId())
                        .userId(booking.getUserId())
                        .carId(booking.getCarId())
                        .rentalStart(booking.getRentalStart())
                        .rentalFinish(booking.getRentalFinish())
                        .status(booking.getStatus())
                        .comment(booking.getComment())
                        .build()
                )
                .collect(toList());
    }

    public List<BookingDto> findBookingsByUserId (Integer userId) {
        var bookingDtos = findAll();
        return bookingDtos.stream()
                .filter(bookingDto -> bookingDto.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public void create(CreateBookingDto createBookingDto) {
        Booking booking = createBookingMapper.mapFrom(createBookingDto);
        bookingDao.add(booking);
    }

    public static BookingService getInstance(){
        return INSTANCE;
    }
}
