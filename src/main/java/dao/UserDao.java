package dao;

import entities.Alias;
import entities.Group;
import entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import javax.persistence.Query;

public class UserDao {

    public User findUserById(Session session, int id) {
        return session.get(User.class, id);
    }

    public User findUserByAlias(Session session, String titleAlias) {
        String queryString = "SELECT u FROM User u " +
                "WHERE u.alias = :alias";
        Query query = session.createQuery(queryString);
        AliasDao aliasDao = new AliasDao();
        Alias alias = aliasDao.findAliasByTitle(session, titleAlias);
        query.setParameter("alias", alias);
        return (User) query.getResultList().get(0);
    }

    public void save(Session session, User user) {
        session.persist(user);
    }

    public void update(Session session, User user) {
        session.merge(user);
    }

    public void delete(Session session, User user) {
        session.remove(user);
    }
}
