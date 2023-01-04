package rentCars;

import rentCars.dao.BookingDao;
import rentCars.dao.CarDao;
import rentCars.dao.ClientDao;
import rentCars.entity.Booking;
import rentCars.entity.Car;
import rentCars.entity.Client;
import rentCars.entity.enums.BookingStatusEnum;
import rentCars.entity.enums.CarColorEnum;
import rentCars.entity.enums.CarStatusEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class DaoRentCarsRunner {
    public static void main(String[] args) {
        //addCar();
        //deleteCar();
        //updateCar();
        //findAllCars();

        //addClient();
        //deleteClient();
        //findAllClients();
        //updateClient();
        //findClientWithFilters();

        //addBooking();
        //deleteBooking();
        //findAllBookings();
        //updateBooking();
    }

    private static void findClientByUserId() {
        Optional<Integer> clientIdByUserId = ClientDao.getInstance().findClientIdByUserId(2);
        System.out.println(clientIdByUserId);
    }


    private static void updateBooking() {
        BookingDao bookingDao = BookingDao.getInstance();
        Optional<Booking> maybeBooking = bookingDao.findById(3L);
        System.out.println(maybeBooking);

        maybeBooking.ifPresent(booking -> {
            booking.setCarId(13);
            bookingDao.update(booking);
        });
    }

    private static void findAllBookings() {
        List<Booking> allBookings = BookingDao.getInstance().findAll();
        System.out.println(allBookings);
    }

    private static void deleteBooking() {
        BookingDao bookingDao = BookingDao.getInstance();
        boolean deleteBooking = bookingDao.delete(7L);
        System.out.println(deleteBooking);
    }

    private static void addBooking() {
        BookingDao bookingDao = BookingDao.getInstance();
        Booking booking = new Booking();
        booking.setUserId(7);
        booking.setCarId(7);
        booking.setRentalStart(LocalDateTime.of(2022, 11, 23, 19, 46, 0));
        booking.setRentalFinish(LocalDateTime.of(2022, 12, 21, 19, 46, 0));
        booking.setStatus(BookingStatusEnum.APPROVED);
        booking.setComment("Замечаний нет");

        Booking addBooking = bookingDao.add(booking);
        System.out.println(addBooking);
    }


    private static void updateClient() {
        ClientDao clientDao = ClientDao.getInstance();
        Optional<Client> maybeClient = clientDao.findById(9);
        System.out.println(maybeClient);

        maybeClient.ifPresent(client -> {
            client.setAge(35);
            clientDao.update(client);
        });
    }

    private static void findAllClients() {
        List<Client> allClients = ClientDao.getInstance().findAll();
        System.out.println(allClients);
    }

    private static void deleteClient() {
        ClientDao clientDao = ClientDao.getInstance();
        boolean deleteClient = clientDao.delete(10);
        System.out.println(deleteClient);
    }

    private static void addClient() {
        ClientDao clientDao = ClientDao.getInstance();
        Client client = new Client();
        client.setAge(28);
        client.setLicenceNo(100916);
        client.setValidity(LocalDate.of(2029, 3, 11));


        Client addedClient = clientDao.add(client);
        System.out.println(addedClient);
    }


    private static void findAllCars() {
        var cars = CarDao.getInstance().findAll();
        System.out.println(cars);
    }

    private static void updateCar() {
        var carDao = CarDao.getInstance();
        var maybeCar = carDao.findById(7);
        System.out.println(maybeCar);

        maybeCar.ifPresent(car -> {
            car.setStatus(CarStatusEnum.BOOKED);
            carDao.update(car);
        });
    }

    private static void deleteCar() {
        var carDao = CarDao.getInstance();
        var deleteCar = carDao.delete(18);
        System.out.println(deleteCar);
    }

    private static void addCar() {
        var carDao = CarDao.getInstance();
        var car = new Car();
        car.setBrand("Audi RS6");
        car.setColor(CarColorEnum.Red);
        car.setSeatAmount(5);
        car.setPrice(18150);
        car.setStatus(CarStatusEnum.AVAILABLE);

        var addedCar = carDao.add(car);
        System.out.println(addedCar);
    }
}
