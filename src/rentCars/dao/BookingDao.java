package rentCars.dao;

import rentCars.entity.Booking;
import rentCars.entity.enums.BookingStatusEnum;
import rentCars.exception.RentCarsDaoException;
import rentCars.util.RentCarsConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingDao implements DaoRentCar<Long, Booking> {
    public static final BookingDao INSTANCE = new BookingDao();
    private final ClientDao clientDao = ClientDao.getInstance();
    private final CarDao carDao = CarDao.getInstance();

    public static final String DELETE_BOOKING_SQL = """
            DELETE FROM booking
            WHERE id = ?
            """;

    public static final String ADD_BOOKING_SQL = """
            INSERT INTO booking (user_id, car_id, rental_start, rental_finish, status, comment) 
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    public static final String UPDATE_BOOKING_SQL = """
            UPDATE booking
            SET user_id = ?,
            car_id = ?,
            rental_start = ?,
            rental_finish = ?,
            status = ?,
            comment = ?
            WHERE id = ?
            """;

    public static final String FIND_ALL_BOOKINGS_SQL = """
            SELECT id,
            user_id,
            car_id,
            rental_start,
            rental_finish,
            status,
            comment
            FROM booking
            """;

    public static final String FIND_BOOKING_BY_ID = FIND_ALL_BOOKINGS_SQL + """
            WHERE id = ?
            """;


    private BookingDao() {}

    @Override
    public void update(Booking booking) {
        try (Connection connection = RentCarsConnectionManager.open();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOKING_SQL)) {
            preparedStatement.setLong(1, booking.getUserId());
            preparedStatement.setLong(2, booking.getCarId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(booking.getRentalStart()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(booking.getRentalFinish()));
            preparedStatement.setString(5, booking.getStatus().name());
            preparedStatement.setString(6, booking.getComment());
            preparedStatement.setLong(7, booking.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }


    @Override
    public Optional<Booking> findById(Long id) {
        try (Connection connection = RentCarsConnectionManager.open();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BOOKING_BY_ID)) {
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        Booking booking = null;
        if (resultSet.next()) {
            booking = buildBooking(resultSet);
        }
        return Optional.ofNullable(booking);
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    @Override
    public List<Booking> findAll() {
        try (Connection connection = RentCarsConnectionManager.open();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BOOKINGS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Booking> bookings = new ArrayList<>();
            while (resultSet.next()) {
                bookings.add(buildBooking(resultSet));
            }
            return bookings;
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    private Booking buildBooking(ResultSet resultSet) throws SQLException {
        return new Booking(
                resultSet.getLong("id"),
                resultSet.getInt("user_id"),
                resultSet.getInt("car_id"),
                resultSet.getTimestamp("rental_start").toLocalDateTime(),
                resultSet.getTimestamp("rental_finish").toLocalDateTime(),
                BookingStatusEnum.valueOf(resultSet.getObject("status", String.class)),
                resultSet.getString("comment")
        );
    }

    @Override
    public Booking add(Booking booking) {
        try (Connection connection = RentCarsConnectionManager.open();
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_BOOKING_SQL, Statement.RETURN_GENERATED_KEYS)) {
        preparedStatement.setInt(1, booking.getUserId());
        preparedStatement.setInt(2, booking.getCarId());
        preparedStatement.setTimestamp(3, Timestamp.valueOf(booking.getRentalStart()));
        preparedStatement.setTimestamp(4, Timestamp.valueOf(booking.getRentalFinish()));
        preparedStatement.setString(5, booking.getStatus().name());
        preparedStatement.setString(6, booking.getComment());

        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        while (generatedKeys.next()) {
            booking.setId(generatedKeys.getLong("id"));
        }
        return booking;
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = RentCarsConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOKING_SQL)) {
        preparedStatement.setLong(1, id);
        return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    public static BookingDao getInstance() {
        return INSTANCE;
    }
}
