package dao;

import entities.Alias;
import entities.PrivateContact;
import org.hibernate.Session;

public class PrivateContactDao {

    public void save(Session session, PrivateContact contact) {
        session.persist(contact);
    }

    public void update(Session session, PrivateContact contact) {
        session.merge(contact);
    }

    public void delete(Session session, PrivateContact contact) {
        session.remove(contact);
    }
}
