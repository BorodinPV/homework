package lesson15.src.main.java;

import config.JdbcConfig;

import java.sql.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Created by Pavel Borodin on 2019-07-01
 */
public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = JdbcConfig.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Statement statement = null;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO postgres.public.user (name, birthday, login_id, city, email, description) " +
                "VALUES (?,?,?,?,?,?)";
        String querySelect = "SELECT * FROM postgres.public.user WHERE login_id = ? and name = ?";

        try {
            executeQuery(connection, statement);
            prepareStatExecute(connection, preparedStatement, query);
            batchQuery(connection, statement);
            selectQuery(connection, preparedStatement, querySelect);
            savepointQuery(connection, statement, query); //postgres 7.0

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void savepointQuery(Connection connection, Statement statement, String query) throws SQLException {
        statement = Objects.requireNonNull(connection).createStatement();
        connection.setAutoCommit(false);
        Savepoint savepoint = connection.setSavepoint();

        statement.execute("INSERT INTO postgres.public.user (name, birthday, login_id, city, email, description) " +
                "VALUES ('Vladimir', '1993-06-07', '123','Moscow','example@example.com', 'null')");
        try {
            statement.execute("INSERT INTO postgres.public.user (name, birthday, login_id, city, email, description) " +
                    "VALUES ('Vladimir', 'test', '123','Moscow','example@example.com', 'null')");
            connection.commit();
        } catch (Exception e) {
            connection.rollback(savepoint);
            e.printStackTrace();
        }
    }

    private static void selectQuery(Connection connection, PreparedStatement preparedStatement, String querySelect) throws SQLException {
        preparedStatement = connection.prepareStatement(querySelect);
        preparedStatement.setInt(1, 123);
        preparedStatement.setString(2, "batchTest2");
        ResultSet rs = preparedStatement.executeQuery();
        while ( rs.next() ) {
            String name = rs.getString("name");
            String login_id = rs.getString("login_id");

            System.out.println(name + " " + login_id);
        }
    }

    private static void batchQuery(Connection connection, Statement statement) throws SQLException {
        statement = connection.createStatement();
        statement.addBatch("INSERT INTO postgres.public.user (name, birthday, login_id, city, email, description) " +
                "VALUES ('batchTest1', '1993-06-07', '123','Moscow','example@example.com', 'null')");
        statement.addBatch("INSERT INTO postgres.public.user (name, birthday, login_id, city, email, description) " +
                "VALUES ('batchTest2', '1993-06-07', '123','Moscow','example@example.com', 'null')");
        statement.executeBatch();
    }

    private static void executeQuery(Connection connection, Statement statement) throws SQLException {
        statement = Objects.requireNonNull(connection).createStatement();
        statement.execute("INSERT INTO postgres.public.user (name, birthday, login_id, city, email, description) " +
                "VALUES ('Vladimir', '1993-06-07', '123','Moscow','example@example.com', 'null')");
    }

    private static void prepareStatExecute(Connection connection, PreparedStatement preparedStatement, String query) throws SQLException {
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, "name");
        preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
        preparedStatement.setInt(3, 123);
        preparedStatement.setString(4, "Moscow");
        preparedStatement.setString(5, "michael@example.com");
        preparedStatement.setString(6, "IT Department");

        preparedStatement.executeUpdate();
    }
}
