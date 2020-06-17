package service;

import dao.MessageDao;
import entities.Message;

import java.util.logging.Logger;

public class MessageService {
    private static Logger log = Logger.getLogger(MessageService.class.getName());

    private MessageDao messageDao = new MessageDao();

    public void writeNewMessage(Message message) {
        messageDao.save(message);
        log.info(String.format("new message from %s to %s",
                message.getUser().getAlias().getTitle(), message.getAlias().getTitle()));
    }
}
