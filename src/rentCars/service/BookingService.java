package rentCars.service;

import rentCars.dao.BookingDao;
import rentCars.dao.CarDao;
import rentCars.dao.ClientDao;
import rentCars.dto.BookingDto;
import rentCars.dto.CreateDto.CreateBookingDto;
import rentCars.entity.Booking;
import rentCars.entity.Car;
import rentCars.entity.Client;
import rentCars.entity.enums.BookingStatusEnum;
import rentCars.entity.enums.CarStatusEnum;
import rentCars.mapper.CreateBookingMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static rentCars.entity.enums.BookingStatusEnum.IN_PROGRESS;

public class BookingService {
    private static final Integer ALLOWABLE_AGE = 18;
    private static final BookingService INSTANCE = new BookingService();
    private final BookingDao bookingDao = BookingDao.getInstance();
    private final CreateBookingMapper createBookingMapper = CreateBookingMapper.getInstance();
    private final CarDao carDao = CarDao.getInstance();
    private final ClientDao clientDao = ClientDao.getInstance();

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


    public Integer findCarIdByBookingId (Integer bookingId) {
        return bookingDao.findCarIdByBookingId(Long.valueOf(bookingId));
    }

    public Integer findUserIdByBookingId (Integer bookingId) {
        return bookingDao.findUserIdByBookingId(Long.valueOf(bookingId));
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


    public void sendCancelBookingMessage(BookingDto bookingDto) {
        String cancelBookingMessage = "Please, cancel booking.";

        Optional<Booking> booking = bookingDao.findById(bookingDto.getId());
        if (booking.isPresent()) {
            Booking bookingToUpdate = Booking.builder()
                    .id(bookingDto.getId())
                    .userId(bookingDto.getUserId())
                    .carId(bookingDto.getCarId())
                    .rentalStart(bookingDto.getRentalStart())
                    .rentalFinish(bookingDto.getRentalFinish())
                    .status(bookingDto.getStatus())
                    .comment(cancelBookingMessage)
                    .build();
            bookingDao.update(bookingToUpdate);
        }
    }

    public void completeBooking(BookingDto bookingDto) {
        Long completedBookingId = bookingDto.getId();
        Integer completedCarId = bookingDto.getCarId();
        String completedMessage = "Your booking is completed. Nice to meet you again!";

        Optional<Car> car = carDao.findById(completedCarId);
        if (car.isPresent()) {
            Car carToUpdate = Car.builder()
                    .id(bookingDto.getCarId())
                    .status(CarStatusEnum.AVAILABLE)
                    .build();
            carDao.updateCarFromBooking(carToUpdate);
        }

        Optional<Booking> booking = bookingDao.findById(completedBookingId);
        if (booking.isPresent()) {
            Booking bookingToUpdate = Booking.builder()
                    .id(bookingDto.getId())
                    .userId(bookingDto.getUserId())
                    .carId(bookingDto.getCarId())
                    .rentalStart(bookingDto.getRentalStart())
                    .rentalFinish(bookingDto.getRentalFinish())
                    .status(BookingStatusEnum.COMPLETED)
                    .comment(completedMessage)
                    .build();
            bookingDao.update(bookingToUpdate);
        }
    }


    public void cancelBooking(BookingDto bookingDto) {
        Long cancelledBookingId = bookingDto.getId();
        Integer cancelledCarId = bookingDto.getCarId();
        String cancelMessage = "Your booking is cancelled. Wait to see you again!";

        Optional<Car> car = carDao.findById(cancelledCarId);
        if (car.isPresent()) {
            Car carToUpdate = Car.builder()
                    .id(bookingDto.getCarId())
                    .status(CarStatusEnum.AVAILABLE)
                    .build();
            carDao.updateCarFromBooking(carToUpdate);
        }

        Optional<Booking> booking = bookingDao.findById(cancelledBookingId);
        if (booking.isPresent()) {
            Booking bookingToUpdate = Booking.builder()
                    .id(bookingDto.getId())
                    .userId(bookingDto.getUserId())
                    .carId(bookingDto.getCarId())
                    .rentalStart(bookingDto.getRentalStart())
                    .rentalFinish(bookingDto.getRentalFinish())
                    .status(BookingStatusEnum.CANCELLED)
                    .comment(cancelMessage)
                    .build();
            bookingDao.update(bookingToUpdate);
        }
    }


    public void checkBooking(BookingDto bookingDto) {
        Integer userId = bookingDto.getUserId();
        Optional<Integer> clientIdByUserId = clientDao.findClientIdByUserId(userId);
        Optional<Client> client = clientDao.findById(clientIdByUserId.orElseThrow());
        Integer clientAge = client.orElseThrow().getAge();
        LocalDate clientLicenceValidity = client.orElseThrow().getValidity();
        LocalDateTime rentalStart = bookingDto.getRentalStart();
        LocalDateTime rentalFinish = bookingDto.getRentalFinish();
        Integer pricePerDay = carDao.findById(bookingDto.getCarId()).orElseThrow().getPrice();

        double finalPrice = calculatePrice(pricePerDay, rentalStart, rentalFinish);

        if (bookingDto.getStatus().equals(IN_PROGRESS)) {
            String allowableAgeMessage = "";
            if (isNotAllowableAge(clientAge)) {
                allowableAgeMessage = "Your age must be greater than or equal to 18.";
            }

            String validLicenceMessage = "";
            if (isNotValidLicence(clientLicenceValidity, rentalStart, rentalFinish)) {
                validLicenceMessage = "Your driving licence is not valid during var booking.";
            }

            String correctPeriodOfBookingMessage = "";
            if (isNotCorrectPeriodOfBooking(rentalStart, rentalFinish)) {
                correctPeriodOfBookingMessage = "Your choose incorrect period of booking. Please, check dates of car booking.";
            }

            String final_message = "Everything is OK! - Have a nice trip! ";
            BookingStatusEnum bookingStatusEnum = BookingStatusEnum.APPROVED;

            if ((isNotAllowableAge(clientAge)) ||
                (isNotValidLicence(clientLicenceValidity, rentalStart, rentalFinish)) ||
                (isNotCorrectPeriodOfBooking(rentalStart, rentalFinish))) {
                bookingStatusEnum = BookingStatusEnum.REJECTED;
                final_message = String.format("%s %s %s", allowableAgeMessage, validLicenceMessage, correctPeriodOfBookingMessage);

            } else {
                final_message = final_message + "Total price: " + finalPrice + " rubles";


                Optional<Car> car = carDao.findAvailableCarById(bookingDto.getCarId());
                if (car.isPresent()) {
                    Car carToUpdate = Car.builder()
                            .id(bookingDto.getCarId())
                            .status(CarStatusEnum.BOOKED)
                            .build();
                    carDao.updateCarFromBooking(carToUpdate);
                }
            }

            Optional<Booking> booking = bookingDao.findById(bookingDto.getId());
            if (booking.isPresent()) {
                Booking bookingToUpdate = Booking.builder()
                        .id(bookingDto.getId())
                        .userId(bookingDto.getUserId())
                        .carId(bookingDto.getCarId())
                        .rentalStart(bookingDto.getRentalStart())
                        .rentalFinish(bookingDto.getRentalFinish())
                        .status(bookingStatusEnum)
                        .comment(final_message)
                        .build();
                bookingDao.update(bookingToUpdate);
            }
        }
    }


    private boolean isNotCorrectPeriodOfBooking(LocalDateTime rentalStart, LocalDateTime rentalFinish) {
        return (rentalStart.toLocalDate()).isAfter(rentalFinish.toLocalDate());
    }

    private boolean isNotValidLicence(LocalDate clientLicenceValidity, LocalDateTime rentalStart, LocalDateTime rentalFinish) {
        return clientLicenceValidity.isBefore(rentalStart.toLocalDate()) || clientLicenceValidity.isBefore(rentalFinish.toLocalDate());
    }

    private boolean isNotAllowableAge(Integer clientAge) {
        return clientAge < BookingService.ALLOWABLE_AGE;
    }

    private double calculatePrice(Integer pricePerDay, LocalDateTime rentalStart, LocalDateTime rentalFinish) {
        long amountOfDays = ChronoUnit.DAYS.between(rentalStart, rentalFinish);
        return pricePerDay.doubleValue() * amountOfDays;
    }

    public static BookingService getInstance(){
        return INSTANCE;
    }
}
