package tservices;

import entities.Alias;
import entities.Group;
import entities.Message;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.GroupRepository;
import repositories.IMessageRepository;
import repositories.MessageRepository;

import java.util.List;

@Component
public class TMessageService {

    @Autowired
    private MessageRepository repository;

    @Autowired
    private IMessageRepository iMessageRepository;

    @Transactional
    public void add(Message message) {
        repository.persist(message);
    }

    @Transactional
    public List<Message> findMessagesToConsumer(Alias consumer) {
        return iMessageRepository.findMessageByAlias(consumer);
    }

    @Transactional
    public List<Message> findMessagesFromSender(User sender) {
        return iMessageRepository.findMessageByUser(sender);
    }

    @Transactional
    public List<Message> findMessagesFromSenderToConsumer(User sender, Alias consumer) {
        return iMessageRepository.findMessageByUserAndAlias(sender, consumer);
    }
}
