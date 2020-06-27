package repositories;

import entities.Alias;
import entities.Message;
import entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMessageRepository extends JpaRepository<Message, Integer> {

    /**
     * Find a message to a consumer
     * @param alias consumer alias
     * @return
     */
    List<Message> findMessageByAlias(Alias alias);

    /**
     * Find a message from a sender
     * @param user sender
     * @return
     */
    List<Message> findMessageByUser(User user);

    /**
     * Find a message from a sender to a consumer
     * @param user sender
     * @param alias consumer
     * @return
     */
    List<Message> findMessageByUserAndAlias(User user, Alias alias);
}
