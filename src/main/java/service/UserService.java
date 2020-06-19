package service;

import dao.GroupDao;
import dao.UserDao;
import entities.User;
import org.hibernate.Session;
import util.HibernateSessionFactoryUtil;

import java.util.logging.Logger;

public class UserService {
    private static Logger log = Logger.getLogger(UserService.class.getName());

    private UserDao userDao = new UserDao();

    public void addNewUser(User user) {
        userDao.save(getSession(), user);
        log.info(String.format("user with alias=%s has been added",
                user.getAlias().getTitle()));
    }

    private Session getSession() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }
}
