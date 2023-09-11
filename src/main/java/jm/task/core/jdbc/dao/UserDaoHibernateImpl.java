package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;
import static org.hibernate.resource.transaction.spi.TransactionStatus.ACTIVE;
import static org.hibernate.resource.transaction.spi.TransactionStatus.MARKED_ROLLBACK;


public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory = getSessionFactory();
    private Transaction transaction = null;


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable()  {

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            Query query = session.createSQLQuery("create table if not exists users\n" +
                    "(\n" +
                    "    id       int auto_increment,\n" +
                    "    name     varchar(50) null,\n" +
                    "    lastname varchar(50) null,\n" +
                    "    age      TINYINT     null,\n" +
                    "    constraint users_pk\n" +
                    "        primary key (id)\n" +
                    ");\n" +
                    "\n").addEntity(User.class);
            query.executeUpdate();

            transaction.commit();

        } catch (HibernateException e) {
            System.out.println("Ошибка в методе createUserTable");
        }

        if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
            transaction.rollback();

        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS users").addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("ошибка в методе dropUsersTable");
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("ошибка в методе saveUser");
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();

            }
        }
    }

    @Override
    public void removeUserById(long id)  {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("ошибка в методе saveUser");
            if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
                transaction.rollback();
            }

        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
            criteriaQuery.from(User.class);
            transaction = session.beginTransaction();
            users = session.createQuery(criteriaQuery).getResultList();
            transaction.commit();
            return users;
        } catch (HibernateException e) {
            System.out.println("ошибка в методе getAllUsers");
        }
        if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
            transaction.rollback();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("TRUNCATE TABLE users");
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("ошибка в методе saveUser");
        }
        if (transaction.getStatus() == ACTIVE || transaction.getStatus() == MARKED_ROLLBACK) {
            transaction.rollback();
        }

    }


}