package connect_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class UtilityDAO {
    String dbms = "mysql";
    String serverName = "localhost";
    int portNumber = 3306;
    String dbName = "chat_system";
    String user = "root";
    String password = "3333";

    public Connection getConnection() {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", user);
        connectionProps.put("password", password);

        try {
            String connString = "jdbc:" + dbms + "://" + serverName + ":" + portNumber + "/";
            conn = DriverManager.getConnection(connString, connectionProps);
            conn.setCatalog(dbName);

            // Thông báo kết nối thành công
            System.out.println("Connection to database '" + dbName + "' established successfully!");
        } catch (SQLException e) {
            // Thông báo kết nối thất bại
            System.err.println("Failed to connect to the database. Error: " + e.getMessage());
            conn = null;
        }
        return conn;
    }
}
