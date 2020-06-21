package service;

import dao.AliasDao;
import dao.MessageDao;
import dao.UserDao;
import entities.Alias;
import entities.Group;
import entities.Message;
import entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class MessageService {
    private static Logger log = Logger.getLogger(MessageService.class.getName());

    private MessageDao messageDao = new MessageDao();

    public Message findMessageById(Session session, int id) {
        return messageDao.findMessageById(session, id);
    }

    public void writeNewMessage(Session session, Message message) {
        messageDao.save(session, message);
        log.info(String.format("new message from %s to %s",
                message.getUser().getAlias().getTitle(), message.getAlias().getTitle()));
    }

    public List<String> getAllMessagesInGroup(Group group) {
        List<Message> list = messageDao.getAllMessagesInGroup(getSession(), group);
        List<String> result = new ArrayList<>();
        list.stream().forEach( message ->
                result.add(String.format("group %s has message %s from %s",
                        group.getAlias().getTitle(), message.getText(),
                        group.getUser().getAlias().getTitle()))
        );
        return result;
    }

    public List<String> getAllMessagesToUser(Session session, Alias alias) {
        List<Message> list = messageDao.getAllMessagesToUser(session, alias);
        List<String> result = new ArrayList<>();
        for(Message message : list) {
            result.add(message.getText());
            log.info(String.format("user %s has message %s",
                    alias.getTitle(), message.getText()));
        }
        return result;
    }

    public void sendMessage() {
        Scanner scanner = new Scanner(System.in);
        UserDao userDao = new UserDao();
        AliasDao aliasDao = new AliasDao();
        MessageDao messageDao = new MessageDao();
        Session session = getSession();
        Transaction tx1 = session.beginTransaction();

        System.out.println("Please, write your alias");
        String senderAlias = scanner.nextLine();
        User sender = userDao.findUserByAlias(session, senderAlias);
        System.out.println("Please, write alias of the cosumer");
        String consumerAlias = scanner.nextLine();
        Alias alias = aliasDao.findAliasByTitle(session, consumerAlias);
        System.out.println("Please, write text");
        String text = scanner.nextLine();

        Message message = new Message();
        message.setAlias(alias);
        message.setText(text);
        message.setUser(sender);
        message.setConsumer(alias.getConsumer());

        messageDao.save(session, message);
        tx1.commit();
        session.close();
    }

    Session getSession() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }
}
