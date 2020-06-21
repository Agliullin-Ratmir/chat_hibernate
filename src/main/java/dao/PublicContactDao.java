package dao;

import entities.PrivateContact;
import entities.PublicContact;
import org.hibernate.Session;

public class PublicContactDao {

    public void save(Session session, PublicContact contact) {
        session.persist(contact);
    }

    public void update(Session session, PublicContact contact) {
        session.merge(contact);
    }

    public void delete(Session session, PublicContact contact) {
        session.remove(contact);
    }

}
