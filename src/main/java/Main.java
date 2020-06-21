import dao.AliasDao;
import dao.PrivateContactDao;
import dao.UserDao;
import entities.Alias;
import entities.PrivateContact;
import enums.Consumers;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.AliasService;
import service.GroupService;
import service.MessageService;
import util.HibernateSessionFactoryUtil;

public class Main {

    public static void main(String[] args) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        tx.commit();
        session.close();
        System.out.println("Done");
    }
}
