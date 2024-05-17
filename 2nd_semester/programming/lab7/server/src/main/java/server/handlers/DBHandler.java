package server.handlers;

import common.collections.LabWork;
import common.exceptions.UserException;
import common.handlers.IOHandler;
import common.network.User;

import java.sql.*;
import java.util.ArrayDeque;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBHandler implements common.handlers.DBHandler {
    private String jdbcUrl;
    private String username;
    private String password;
    private static final Logger logger = Logger.getLogger("logger");

    public DBHandler(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;

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

    public Connection getConnection(){
        Connection connection = null;

        try{
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e){
            logger.log(Level.SEVERE, "Could not connect to database: " + e.getMessage());
            System.exit(0);
        }

        return connection;
    }

    public User checkUserPresence(User user){
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

    public User createUser(User user) throws UserException {
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

    public boolean checkUserPassword(User userToCheck) throws UserException{
        User user = checkUserPresence(userToCheck);

        if (user != null){
            return user.getPassword().equals(userToCheck.getPassword());
        } else{
            throw new UserException("User does not exist");
        }
    }

    public LabWork createLab(LabWork lab, String username, boolean setFields){
        String addLabQuery;

        if (setFields){
            addLabQuery = "INSERT INTO labworks(id, lab_name, coordinates_x, coordinates_y, date_created, minimal_point, tuned_in_works, average_point, difficulty, discipline_name, self_study_hours, username) " +
                    "VALUES (DEFAULT, ?,?,?, CURRENT_TIMESTAMP, ?,?,?,?,?,?,?)" +
                    "RETURNING id, date_created;";
        } else {
            addLabQuery = "INSERT INTO labworks(id, lab_name, coordinates_x, coordinates_y, date_created, minimal_point, tuned_in_works, average_point, difficulty, discipline_name, self_study_hours, username) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)" +
                    "RETURNING id, date_created;";
        }

        try(Connection connection = getConnection()){
            try (PreparedStatement stmt = connection.prepareStatement(addLabQuery)){
                if(!setFields){
                    stmt.setInt(1, lab.getId().intValue());
                    stmt.setString(2, lab.getName());
                    stmt.setInt(3, lab.getCoordinates().getX().intValue());
                    stmt.setInt(4, lab.getCoordinates().getY().intValue());
                    stmt.setTimestamp(5, Timestamp.valueOf(lab.getCreationDate()));
                    stmt.setDouble(6, lab.getMinimalPoint());
                    stmt.setInt(7, lab.getTunedInWorks());
                    stmt.setDouble(8, lab.getAveragePoint());
                    stmt.setString(9, lab.getDifficulty().toString());
                    stmt.setString(10, lab.getDiscipline().getName());
                    stmt.setInt(11, lab.getDiscipline().getSelfStudyHours().intValue());
                    stmt.setString(12, lab.getUsername());

                    stmt.execute();

                    return lab;

                } else{
                    stmt.setString(1, lab.getName());
                    stmt.setInt(2, lab.getCoordinates().getX().intValue());
                    stmt.setInt(3, lab.getCoordinates().getY().intValue());
                    stmt.setDouble(4, lab.getMinimalPoint());
                    stmt.setInt(5, lab.getTunedInWorks());
                    stmt.setDouble(6, lab.getAveragePoint());
                    stmt.setString(7, lab.getDifficulty().toString());
                    stmt.setString(8, lab.getDiscipline().getName());
                    stmt.setInt(9, lab.getDiscipline().getSelfStudyHours().intValue());
                    stmt.setString(10, lab.getUsername());

                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            lab.setId(rs.getInt(1));
                            lab.setCreationDate(rs.getTimestamp(2).toLocalDateTime());
                            lab.setUsername(username);

                            return lab;
                        }
                    }
                }
            }
        } catch (SQLException e){
            IOHandler.println(e.getClass().getSimpleName() + ": " + e.getMessage());
            return null;
        }

        return null;
    }

    public ArrayDeque<LabWork> loadCollectionToMemory(){
        String getAllRoutesQuery = "SELECT * FROM labworks;";
        ArrayDeque<LabWork> collection = new ArrayDeque<>();

        try(Connection connection = getConnection()){
            try(PreparedStatement stmt = connection.prepareStatement(getAllRoutesQuery)){
                try(ResultSet rs = stmt.executeQuery()){
                    while(rs.next()){
                        try{
                            LabWork lab = new LabWork(
                                    rs.getLong(1),
                                    rs.getString(2),
                                    rs.getLong(3),
                                    rs.getLong(4),
                                    rs.getTimestamp(5).toLocalDateTime(),
                                    rs.getDouble(6),
                                    rs.getInt(7),
                                    rs.getDouble(8),
                                    rs.getString(9),
                                    rs.getString(10),
                                    rs.getLong(11),
                                    rs.getString(12)
                            );
                            LabWork.validateWithOutId(lab);
                            collection.add(lab);
                        } catch (Exception e){
                            IOHandler.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                        }
                    }

                    return collection;
                }
            }
        } catch (SQLException e){
            IOHandler.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        return null;
    }

    public void removeAllUserLabs(User user){
        String removeAllRoutesQuery = "DELETE FROM labworks WHERE username = ?";

        try(Connection connection = getConnection()){
            try(PreparedStatement stmt = connection.prepareStatement(removeAllRoutesQuery)){
                stmt.setString(1, user.getUsername());
                stmt.execute();
            }
        } catch (SQLException e){
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public void removeAllLabs(){
        String removeAllRoutesQuery = "DELETE FROM labworks";

        try(Connection connection = getConnection()){
            try(PreparedStatement stmt = connection.prepareStatement(removeAllRoutesQuery)){
                stmt.execute();
            }
        } catch (SQLException e){
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
