package tservices;

import entities.Group;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.GroupRepository;
import repositories.IGroupRepository;

import java.util.List;

@Component
public class TGroupService {

    @Autowired
    private GroupRepository repository;

    @Autowired
    private IGroupRepository iGroupRepository;

    @Transactional
    public void add(Group group) {
        repository.persist(group);
    }

    @Transactional
    public List<Group> findGroupsWithUser(User user) {
        return iGroupRepository.findByUser(user);
    }

    @Transactional
    public List<Group> getAllGroups() {
        return iGroupRepository.getAllGroups();
    }
}
