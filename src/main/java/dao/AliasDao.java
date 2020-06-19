package dao;

import entities.Alias;
import entities.Message;
import entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

public class AliasDao {

    public Alias findAliasById(Session session, int id) {
        return session.get(Alias.class, id);
    }

    public Alias findAliasByTitle(Session session, String title) {
        String queryString = "SELECT a FROM  Alias a " +
                "WHERE a.title = :alias_title";
        Query query = session.createQuery(queryString);
        query.setParameter("alias_title", title);
        List<Alias> list = query.getResultList();
        return list.get(0);
    }

    public void save(Session session, Alias alias) {
        session.persist(alias);
    }

    public void update(Session session, Alias alias) {
        session.merge(alias);
    }

    public void delete(Session session, Alias alias) {
        session.remove(alias);
    }

}
