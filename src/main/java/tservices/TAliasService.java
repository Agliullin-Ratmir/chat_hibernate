package tservices;

import entities.Alias;
import entities.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.AliasRepository;
import repositories.GroupRepository;

@Component
public class TAliasService {

    @Autowired
    private AliasRepository repository;

    @Transactional
    public void add(Alias alias) {
        repository.persist(alias);
    }
}
