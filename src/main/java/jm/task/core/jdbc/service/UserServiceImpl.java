package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.*;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDaoHibernate = new UserDaoHibernateImpl();
    public void createUsersTable() throws SQLException {
        userDaoHibernate.createUsersTable();

    }

    public void dropUsersTable() throws SQLException {
        userDaoHibernate.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userDaoHibernate.saveUser(name, lastName, age);
        System.out.println(String.join(" ","User с именем", name, "добавлен в базу данных "));

    }

    public void removeUserById(long id) throws SQLException {
        userDaoHibernate.removeUserById(id);


    }

    public List<User> getAllUsers() throws SQLException {
        List <User> users = userDaoHibernate.getAllUsers();

        for(int i =0; i< users.size(); i++)
        {
            System.out.println(users.get(i));
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        userDaoHibernate.cleanUsersTable();

    }
}
