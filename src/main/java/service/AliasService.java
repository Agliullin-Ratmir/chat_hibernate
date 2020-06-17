package service;

import dao.AliasDao;
import entities.Alias;
import enums.Consumers;

import java.util.logging.Logger;

public class AliasService {
    private static Logger log = Logger.getLogger(GroupService.class.getName());

    private AliasDao aliasDao = new AliasDao();

    public void addNewGroupAlias(String title) {
        Alias alias = new Alias();
        alias.setTitle(title);
        alias.setConsumer(Consumers.GROUP);
        aliasDao.save(alias);
        log.info(String.format("new alias for group with title=%s has been added",
                title));
    }

    public void addNewPersonAlias(String title) {
        Alias alias = new Alias();
        alias.setTitle(title);
        alias.setConsumer(Consumers.PERSON);
        aliasDao.save(alias);
        log.info(String.format("new alias for person with title=%s has been added",
                title));
    }
}
