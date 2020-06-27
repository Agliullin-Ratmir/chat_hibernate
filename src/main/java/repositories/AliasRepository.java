package repositories;

import entities.Alias;
import entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AliasRepository {

    @PersistenceContext
    private EntityManager em;

    public void persist(Alias alias) {
        em.persist(alias);
    }
}
