package rentCars.dao;

import rentCars.entity.Car;
import rentCars.entity.enums.CarColorEnum;
import rentCars.entity.enums.CarStatusEnum;
import rentCars.exception.RentCarsDaoException;
import rentCars.util.RentCarsConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDao implements DaoRentCar<Integer, Car> {
    private static final CarDao INSTANCE = new CarDao();

    public static final String DELETE_FROM_CAR_SQL = """
            DELETE FROM car
            WHERE id = ?
            """;

    public static final String ADD_CAR_SQL = """
            INSERT INTO car(brand, color, seat_amount, price, status, image) 
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    public static final String UPDATE_CAR_SQL = """
            UPDATE car
            SET brand = ?,
            color = ?, 
            seat_amount = ?,
            price = ?,
            status = ?,
            image = ?
            WHERE id = ?
            """;

    public static final String FIND_ALL_CARS_SQL = """
            SELECT id,
            brand,
            color,
            seat_amount,
            price, 
            status,
            image
            FROM car
            """;

    public static final String FIND_CAR_BY_ID_SQL = FIND_ALL_CARS_SQL +  """
            WHERE id = ?
            """;

    public static final String FIND_AVAILABLE_CARS = """
            SELECT id,
            brand,
            color,
            seat_amount,
            price, 
            status,
            image
            FROM car
            WHERE status LIKE 'AVAILABLE'
            """;

    private static final String FIND_AVAILABLE_CAR_BY_ID = """
            SELECT id,
            brand,
            color,
            seat_amount,
            price, 
            status,
            image
            FROM car
            WHERE status LIKE 'AVAILABLE' and id = ?
            """;

    public static final String UPDATE_CAR_STATUS_SQL = """
            UPDATE car
            SET status = ?
            WHERE id = ?
            """;


    private CarDao() {}

    @Override
    public Car add(Car car) {
        try (var connection = RentCarsConnectionManager.open();
             var preparedStatement = connection.prepareStatement(ADD_CAR_SQL, Statement.RETURN_GENERATED_KEYS)) {
            addOrUpdateCar(car, preparedStatement);
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                car.setId(generatedKeys.getInt("id"));
            }
            return car;
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    @Override
    public void update(Car car) {
        try (var connection = RentCarsConnectionManager.open();
             var preparedStatement = connection.prepareStatement(UPDATE_CAR_SQL)) {
            addOrUpdateCar(car, preparedStatement);
            preparedStatement.setInt(7, car.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (var connection = RentCarsConnectionManager.open();
             var preparedStatement = connection.prepareStatement(DELETE_FROM_CAR_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    @Override
    public List<Car> findAll() {
        try (var connection = RentCarsConnectionManager.open();
        var preparedStatement = connection.prepareStatement(FIND_ALL_CARS_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                cars.add(buildCar(resultSet));
            }
            return cars;
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    @Override
    public Optional<Car> findById(Integer id) {
        try (Connection connection = RentCarsConnectionManager.open()) {
            return findCarById(id, connection);
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    public Optional<Car> findCarById(Integer id, Connection connection) {
        try (var preparedStatement= connection.prepareStatement(FIND_CAR_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            Car car = null;
            if (resultSet.next()) {
                car = buildCar(resultSet);
            }
            return Optional.ofNullable(car);
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }


    public List<Car> findAvailableCars() {
        try (Connection connection = RentCarsConnectionManager.open();
        var preparedStatement = connection.prepareStatement(FIND_AVAILABLE_CARS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Car> availableCars = new ArrayList<>();
            while (resultSet.next()) {
                availableCars.add(buildCar(resultSet));
            }
            return availableCars;
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    public Optional<Car> findAvailableCarById(Integer id) {
        try (Connection connection = RentCarsConnectionManager.open();
             var preparedStatement= connection.prepareStatement(FIND_AVAILABLE_CAR_BY_ID)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            Car car = null;
            if (resultSet.next()) {
                car = buildCar(resultSet);
            }
            return Optional.ofNullable(car);
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    /**Show info about car - SeeBookingServlet, CheckBookingExistingServlet - checkBooking.jsp, seeBooking.jsp*/
    public Car findNotOptionalCar(Integer id) {
        try (Connection connection = RentCarsConnectionManager.open()) {
            return findNotOptionalCarById(id, connection);
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    public Car findNotOptionalCarById(Integer id, Connection connection) {
        try (var preparedStatement= connection.prepareStatement(FIND_CAR_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            var resultSet = preparedStatement.executeQuery();
            Car car = null;
            if (resultSet.next()) {
                car = buildCar(resultSet);
            }
            return car;
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    public void updateCarFromBooking(Car car) {
        try (Connection connection = RentCarsConnectionManager.open();
             var prepareStatement = connection.prepareStatement(UPDATE_CAR_STATUS_SQL)) {
            prepareStatement.setString(1, car.getStatus().name());
            prepareStatement.setInt(2, car.getId());

            prepareStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }


    private Car buildCar(ResultSet resultSet) throws SQLException {
        return new Car(
                resultSet.getInt("id"),
                resultSet.getString("brand"),
                CarColorEnum.valueOf(resultSet.getObject("color", String.class)),
                resultSet.getInt("seat_amount"),
                resultSet.getInt("price"),
                CarStatusEnum.valueOf(resultSet.getObject("status", String.class)),
                resultSet.getString("image")
        );
    }


    private void addOrUpdateCar(Car car, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, car.getBrand());
        preparedStatement.setString(2, car.getColor().name());
        preparedStatement.setInt(3, car.getSeatAmount());
        preparedStatement.setInt(4, car.getPrice());
        preparedStatement.setString(5, car.getStatus().name());
        preparedStatement.setString(6, car.getImage());
    }


    public static CarDao getInstance() {
        return INSTANCE;
    }
}
