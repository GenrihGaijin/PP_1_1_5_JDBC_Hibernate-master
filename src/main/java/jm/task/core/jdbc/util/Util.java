package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class Util {
    // реализуйте настройку соеденения с БД
private static final String DB_driver = "com.mysql.cj.jdbc.Driver";
    private static final String DB_url = "jdbc:mysql://localhost:3306/dbto";
    private static final String DB_username = "root";
    private static final String DB_password = "va2Aphacikaw`";
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(DB_driver);
            connection = DriverManager.getConnection(DB_url,DB_username,DB_password);

        } catch (ClassNotFoundException | SQLException e ) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/dbto?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "va2Aphacikaw`");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
