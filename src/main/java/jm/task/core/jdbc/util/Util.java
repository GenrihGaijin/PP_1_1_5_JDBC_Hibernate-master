package jm.task.core.jdbc.util;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class Util {
    // реализуйте настройку соеденения с БД
private final String DB_driver = "com.mysql.cj.jdbc.Driver";
    private final String DB_url = "jdbc:mysql://localhost:3306/dbto";
    private final String DB_username = "root";
    private final String DB_password = "va2Aphacikaw`";
    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(DB_driver);
            connection = DriverManager.getConnection(DB_url,DB_username,DB_password);

        } catch (ClassNotFoundException | SQLException e ) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
