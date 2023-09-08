package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection = Util.getConnection();


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable()  {
        try (PreparedStatement preparedStatement = connection.prepareStatement("create table if not exists users\n" +
                "(\n" +
                "    id       int auto_increment,\n" +
                "    name     varchar(50) null,\n" +
                "    lastname varchar(50) null,\n" +
                "    age      TINYINT     null,\n" +
                "    constraint users_pk\n" +
                "        primary key (id)\n" +
                ");\n" +
                "\n");) {

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        }




    public void dropUsersTable()  {
            try(PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS users ");) {

                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }



        }



    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?,?,?)");){

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id)  {

        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?");){

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(ResultSet resultSet = connection.prepareStatement("SELECT * FROM users").executeQuery();) {

            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"), resultSet.getString("lastname"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        }
        catch (SQLException e){
                e.printStackTrace();
            }

        return users;
    }

    public void cleanUsersTable() {
        try(PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE TABLE users");) {

            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }


    }
}
