package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class UtilityDAO {
    String dbms = "mysql";
    String serverName = "localhost";
    int portNumber = 3306;
    String dbName = "";
    String user = "root";
    String password = "";
    public Connection getConnection(){
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", user);
        connectionProps.put("password", password);
        

        try {
            String connString = "jdbc:" + dbms + "://" + serverName +
                                      ":" + this.portNumber + "/";
            conn = DriverManager.getConnection(connString, connectionProps);
            conn.setCatalog(dbName);
        } catch (SQLException e) {
            conn = null;
        }
        return conn;
    }
}