package org.iesvdm.entreno_jsp.dao;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public abstract class AbstractDAOImpl {
    protected static String driverClassName;
    protected static String dbUrl;
    protected static String schemaUrl;
    protected static String schema;
    protected static String username;
    protected static String password;

    static {
        Properties properties = new Properties();
        try {
            properties.load(AbstractDAOImpl.class.getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        driverClassName = properties.getProperty("jdbc.driverClassName");
        dbUrl = properties.getProperty("jdbc.url");
        schema = properties.getProperty("jdbc.schema");
        schemaUrl = dbUrl + schema;
        username = properties.getProperty("jdbc.username");
        password = properties.getProperty("jdbc.password");
    }

    protected static Connection connectDB() throws ClassNotFoundException, SQLException {
        Class.forName(driverClassName);
        Connection conn = DriverManager.getConnection(schemaUrl, username, password);
        return conn;
    }

    protected static void closeDb(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null)
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        if (statement != null)
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
