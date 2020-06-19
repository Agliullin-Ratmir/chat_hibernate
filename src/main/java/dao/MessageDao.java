package dao;

import entities.Alias;
import entities.Group;
import entities.Message;
import entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import javax.persistence.Query;
import java.util.List;

public class MessageDao {

    public Message findMessageById(Session session, int id) {
        return session.get(Message.class, id);
    }

    public void save(Session session, Message message) {
        session.persist(message);
    }

    public void update(Session session, Message message) {
        session.merge(message);
    }

    public void delete(Session session, Message message) {
        session.remove(message);
    }

    public List<Message> getAllMessagesToUser(Session session, Alias alias) {
        String queryString = "SELECT m FROM Message m " +
                "WHERE m.alias = :alias";
        Query query = session.createQuery(queryString);
        query.setParameter("alias", alias);
        return query.getResultList();
    }

    public List<Message> getAllMessagesInGroup(Session session, Group group) {
        String queryString = "SELECT m FROM Message m " +
                "WHERE m.alias = :alias";
        Query query = session.createQuery(queryString);
        query.setParameter("alias", group.getAlias());
        return query.getResultList();
    }
}
