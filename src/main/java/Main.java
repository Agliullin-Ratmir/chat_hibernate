import dao.AliasDao;
import entities.Alias;
import enums.Consumers;
import org.hibernate.Session;
import service.AliasService;
import service.GroupService;
import service.MessageService;
import util.HibernateSessionFactoryUtil;

public class Main {

    public static void main(String[] args) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        MessageService service = new MessageService();
        AliasDao aliasDao = new AliasDao();
        Alias alias = aliasDao.findAliasById(session, 11);
        service.getAllMessagesToUser(session, alias).get(0);
        session.close();
        System.out.println("Done");
    }
}
