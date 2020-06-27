package repositories;

import entities.Group;
import entities.Message;
import entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGroupRepository extends JpaRepository<Message, Integer> {

    /**
     * All groups where the user is a member
     * @param user
     * @return
     */
    List<Group> findByUser(User user);

    /**
     * All groups
     * @return
     */
    @Query("select g from Group g")
    List<Group> getAllGroups();
}
