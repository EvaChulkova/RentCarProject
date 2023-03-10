package rentCars.dao;

import rentCars.entity.Client;
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

public class ClientDao implements DaoRentCar<Integer, Client> {
    public static final ClientDao INSTANCE = new ClientDao();

    public static final String DELETE_CLIENT_SQL = """
            DELETE FROM client
            WHERE id = ?
            """;

    public static final String ADD_CLIENT_SQL = """
            INSERT INTO client(user_id, age, licence_no, validity) 
            VALUES (?, ?, ?, ?)
            """;

    public static final String UPDATE_CLIENT_SQL = """
            UPDATE client
            SET user_id = ?,
            age = ?,
            licence_no = ?,
            validity = ?
            WHERE id = ?
            """;

    public static final String FIND_ALL_CLIENTS_SQL = """
            SELECT id,
            user_id,
            age,
            licence_no,
            validity
            FROM client
            """;

    public static final String FIND_CLIENT_BY_ID_SQL = FIND_ALL_CLIENTS_SQL + """
            WHERE id = ?
            """;

    private static final String FIND_CLIENT_ID_BY_USER_ID = """
            SELECT id
            FROM client 
            WHERE user_id = ?
            """;

    private static final String FIND_CLIENT_BY_USER_ID = """
            SELECT id,
            user_id,
            age,
            licence_no,
            validity
            FROM client
            WHERE user_id = ?
            """;

    private ClientDao() {}

    @Override
    public Client add(Client client) {
        try (Connection connection = RentCarsConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_CLIENT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, client.getUserId());
            preparedStatement.setInt(2, client.getAge());
            preparedStatement.setInt(3, client.getLicenceNo());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(client.getValidity().atStartOfDay()));

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                client.setId(generatedKeys.getInt("id"));
            }
            return client;
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    @Override
    public void update(Client client) {
        try (Connection connection = RentCarsConnectionManager.open();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT_SQL)) {
        preparedStatement.setInt(1, client.getUserId());
        preparedStatement.setInt(2, client.getAge());
        preparedStatement.setInt(3, client.getLicenceNo());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(client.getValidity().atStartOfDay()));
        preparedStatement.setInt(5, client.getId());

        preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (Connection connection = RentCarsConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_SQL)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    @Override
    public List<Client> findAll() {
        try (Connection connection = RentCarsConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_CLIENTS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Client> clients = new ArrayList<>();
            while (resultSet.next()) {
                clients.add(buildClient(resultSet));
            }
            return clients;
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    @Override
    public Optional<Client> findById(Integer id) {
        try (Connection connection = RentCarsConnectionManager.open()) {
            return findClientById(id, connection);
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    public Optional<Client> findClientById(Integer id, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_CLIENT_BY_ID_SQL)) {
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        Client client = null;
        if (resultSet.next()) {
            client = buildClient(resultSet);
        }
        return Optional.ofNullable(client);
        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    /**Personal information - PersonalClientInformationServlet - personalClientInfo.jsp*/
    public Client findClientByUserId(Integer userId) {
        try (Connection connection = RentCarsConnectionManager.open();
             var preparedStatement = connection.prepareStatement(FIND_CLIENT_BY_USER_ID)) {
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            Client client = null;
            if (resultSet.next()) {
                client = buildClient(resultSet);
            }
            return client;

        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }

    /**LoginServlet - login.jsp --- CheckBookingServlet - checkServlet*/
    public Optional<Integer> findClientIdByUserId(Integer userId) {
        try (Connection connection = RentCarsConnectionManager.open();
        var preparedStatement = connection.prepareStatement(FIND_CLIENT_ID_BY_USER_ID)) {
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            Integer id = null;
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            return Optional.ofNullable(id);

        } catch (SQLException throwables) {
            throw new RentCarsDaoException(throwables);
        }
    }


    private Client buildClient(ResultSet resultSet) throws SQLException {
        return Client.builder()
                .id(resultSet.getInt("id"))
                .userId(resultSet.getInt("user_id"))
                .age(resultSet.getInt("age"))
                .licenceNo(resultSet.getInt("licence_no"))
                .validity(resultSet.getDate("validity").toLocalDate())
                .build();
    }


    public static ClientDao getInstance() {
        return INSTANCE;
    }
}
