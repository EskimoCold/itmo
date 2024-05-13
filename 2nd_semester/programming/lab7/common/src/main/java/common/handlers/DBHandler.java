package common.handlers;

import common.exceptions.UserException;
import common.network.User;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBHandler {
    private static final String jdbcUrl = System.getenv("DATABASE_URL");
    private static final String username = System.getenv("DATABASE_USERNAME");
    private static final String password = System.getenv("DATABASE_PASSWORD");
    private static final Logger logger = Logger.getLogger("logger");

    static {
        try(Connection connection = DriverManager.getConnection(jdbcUrl, username, password)){
            try(Statement stmt = connection.createStatement()){
                String createUsersTable = "CREATE TABLE IF NOT EXISTS users" +
                        "(username VARCHAR(100), password VARCHAR(64));";

                String createLWTable = "CREATE TABLE IF NOT EXISTS labworks" +
                        "(id SERIAL PRIMARY KEY, lab_name TEXT, " +
                        "coordinates_x INT, coordinates_y INT," +
                        "date_created TIMESTAMP, " +
                        "minimal_point DOUBLE PRECISION, tuned_in_works INT, average_point DOUBLE PRECISION," +
                        "difficulty TEXT, discipline_name TEXT, self_study_hours INT, username TEXT);";

                stmt.execute(createUsersTable);
                stmt.execute(createLWTable);
            }
        } catch (SQLException e){
            logger.log(Level.SEVERE, e.getMessage());
            System.exit(0);
        }
    }

    public static Connection getConnection(){
        Connection connection = null;

        try{
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e){
            logger.log(Level.SEVERE, "Could not connect to database: " + e.getMessage());
            System.exit(0);
        }

        return connection;
    }

    public static User checkUserPresence(User user){
        Connection connection = getConnection();

        String selectUserQuery = "SELECT * FROM users WHERE username = ? LIMIT 1";

        try (PreparedStatement stmt = connection.prepareStatement(selectUserQuery)){
            stmt.setString(1, user.getUsername());

            try (ResultSet resultSet = stmt.executeQuery()){
                if (resultSet.next()){
                    return new User(resultSet.getString(1), resultSet.getString(2));
                }
            }
        } catch (SQLException e){
            IOHandler.println(e.getMessage());
        }

        return null;
    }

    public static User createUser(User user) throws UserException {
        if(checkUserPresence(user) != null){
            throw new UserException("User already exists");
        }

        String addUserQuery = "INSERT INTO users VALUES(?, ?);";

        try(Connection connection = getConnection()){
            try (PreparedStatement stmt = connection.prepareStatement(addUserQuery)){
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword());

                stmt.executeUpdate();
            }
        } catch (SQLException e){
            return null;
        }

        return user;
    }

    public static boolean checkUserPassword(User userToCheck) throws UserException{
        User user = checkUserPresence(userToCheck);

        if (user != null){
            return user.getPassword().equals(userToCheck.getPassword());
        } else{
            throw new UserException("User does not exist");
        }
    }
}
