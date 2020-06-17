package dao;

import entities.Alias;
import entities.Message;
import entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

public class AliasDao {

    public Alias findAliasById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Alias.class, id);
    }

    public void save(Alias alias) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(alias);
        tx1.commit();
        session.close();
    }

    public void update(Alias alias) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(alias);
        tx1.commit();
        session.close();
    }

    public void delete(Alias alias) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(alias);
        tx1.commit();
        session.close();
    }

}
