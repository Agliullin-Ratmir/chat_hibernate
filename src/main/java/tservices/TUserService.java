package tservices;

import entities.Alias;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.AliasRepository;
import repositories.UserRepository;

@Component
public class TUserService {

    @Autowired
    private UserRepository repository;

    @Transactional
    public void add(User user) {
        repository.persist(user);
    }
}
