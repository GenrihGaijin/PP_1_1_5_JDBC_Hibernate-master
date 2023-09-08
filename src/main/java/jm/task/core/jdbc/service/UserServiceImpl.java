package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.*;
import jm.task.core.jdbc.model.User;



import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDaoHibernate = new UserDaoHibernateImpl();
    public void createUsersTable()  {
        userDaoHibernate.createUsersTable();

    }

    public void dropUsersTable() {
        userDaoHibernate.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age)  {
        userDaoHibernate.saveUser(name, lastName, age);
        System.out.println(String.join(" ","User с именем", name, "добавлен в базу данных "));

    }

    public void removeUserById(long id)  {
        userDaoHibernate.removeUserById(id);


    }

    public List<User> getAllUsers() {
        List <User> users = userDaoHibernate.getAllUsers();

        for(int i =0; i< users.size(); i++)
        {
            System.out.println(users.get(i));
        }
        return users;
    }

    public void cleanUsersTable()  {
        userDaoHibernate.cleanUsersTable();

    }
}
