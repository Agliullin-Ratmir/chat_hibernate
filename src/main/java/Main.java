import org.hibernate.Session;
import util.HibernateSessionFactoryUtil;

public class Main {

    public static void main(String[] args) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.close();
        System.out.println("Done");
    }
}
