package service;

import dao.AliasDao;
import entities.Alias;
import entities.Group;
import entities.Message;
import entities.User;
import enums.Consumers;
import enums.Roles;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import tservices.TAliasService;
import tservices.TGroupService;
import tservices.TMessageService;
import tservices.TUserService;
import util.PersistenceJPAConfig;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = PersistenceJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RepositoryTest {

    @Autowired
    private TGroupService tGroupService;

    @Autowired
    private TMessageService tMessageService;

    private User sender;
    private User consumer;
    private Alias senderAlias;
    private Alias consumerAlias;
    private Group group;
    private Message message;
    private String text = "Hello!";

    @Before
    public void setUp() {
        senderAlias = getSenderAlias();
        consumerAlias = getConsumerAlias();
        sender = getSender(senderAlias);
        consumer = getConsumer(consumerAlias);
        message = getNewMessage(sender, consumer);
        group = getGroup(getGroupAlias(), sender);
    }

    @Test
    @Transactional
    public void testMessageRepository() {
        tMessageService.add(message);
        List<Message> messages = tMessageService.findMessagesFromSender(sender);
        assertNotNull(messages);
        assertEquals(1, messages.size());
        assertEquals(text, messages.get(0).getText());
    }

    @Test
    @Transactional
    public void testGroupRepository() {
        tGroupService.add(group);
        List<Group> groups = tGroupService.getAllGroups();
        assertNotNull(groups);
        assertEquals(1, groups.size());
    }

    private Message getNewMessage(User sender, User consumer) {
        Message message = new Message();
        message.setText(text);
        message.setUser(sender);//user for sender
        message.setAlias(consumer.getAlias());//alias for consumer
        message.setConsumer(Consumers.PERSON);//consumerType for a person
        return message;
    }

    private Alias getSenderAlias() {
        Alias alias = new Alias();
        alias.setConsumer(Consumers.PERSON);
        alias.setTitle("Batman");
        return alias;
    }

    private Alias getConsumerAlias() {
        Alias alias = new Alias();
        alias.setConsumer(Consumers.PERSON);
        alias.setTitle("Superman");
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

    private Alias getGroupAlias() {
        Alias alias = new Alias();
        alias.setConsumer(Consumers.GROUP);
        alias.setTitle("JL");
        return alias;
    }

    private Group getGroup(Alias aliasForGroup, User user) {
        Group group = new Group();
        group.setRole(Roles.USER);
        group.setAlias(aliasForGroup);
        group.setUser(user);
        return group;
    }
}
