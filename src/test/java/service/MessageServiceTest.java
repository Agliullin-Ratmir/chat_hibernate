package service;


import dao.AliasDao;
import dao.MessageDao;
import dao.UserDao;
import entities.Alias;
import entities.Message;
import entities.User;
import enums.Consumers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MessageServiceTest {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    @Before
    public void before() {
        sessionFactory = createSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    @Test
    public void testWriteMessage() {
        String text = "Hello";
        Alias senderAlias = getSenderAlias();
        Alias consumerAlias = getConsumerAlias();
        UserDao userDao = new UserDao();

        User sender = getSender(senderAlias);
        User consumer = getConsumer(consumerAlias);
        userDao.save(session, sender);
        userDao.save(session, consumer);

        Message message = new Message();
        MessageService service = new MessageService();
        message.setConsumer(Consumers.PERSON);
        message.setText(text);
        message.setUser(sender);
        message.setAlias(consumerAlias);
        service.writeNewMessage(session, message);

        List<String> list = service.getAllMessagesToUser(session, consumerAlias);
        assertNotNull(list);
        assertEquals(1, list.size());
    }

    private Alias getSenderAlias() {
        Alias alias = new Alias();
        AliasDao aliasDao = new AliasDao();
        alias.setConsumer(Consumers.PERSON);
        alias.setTitle("Batman");
        aliasDao.save(session, alias);
        return alias;
    }

    private Alias getConsumerAlias() {
        Alias alias = new Alias();
        AliasDao aliasDao = new AliasDao();
        alias.setConsumer(Consumers.PERSON);
        alias.setTitle("Superman");
        aliasDao.save(session, alias);
        return alias;
    }

    private User getSender(Alias alias) {
        User user  = new User();
        user.setAlias(alias);
        user.setEmail("batman@mail.ru");
        user.setFirstName("Bruce");
        user.setLastName("Wayne");
        return user;
    }

    private User getConsumer(Alias alias) {
        User user  = new User();
        user.setAlias(alias);
        user.setEmail("super@mail.ru");
        user.setFirstName("Klark");
        user.setLastName("Kent");
        return user;
    }

    private SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Message.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Alias.class);
        configuration.setProperty("hibernate.dialect",
                "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class",
                "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:./data/db");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.show_sql", "true");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }

    @After
    public void after() {
        session.close();
        sessionFactory.close();
    }
}
