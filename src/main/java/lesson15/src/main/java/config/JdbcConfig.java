package config;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Pavel Borodin on 2019-07-01
 */
public class JdbcConfig {
    private static final Logger log = Logger.getLogger(JdbcConfig.class);
    private static JdbcConfig instance;
    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String username = "postgres";
    private String password = "123456";

    private JdbcConfig() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        if (connection != null) {
            log.info("Connected to the database!");
        } else {
            log.error("Failed to make connection!");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static JdbcConfig getInstance() throws SQLException {
        if (instance == null) {
            instance = new JdbcConfig();
        } else if (instance.getConnection().isClosed()) {
            instance = new JdbcConfig();
        }
        return instance;
    }
}
