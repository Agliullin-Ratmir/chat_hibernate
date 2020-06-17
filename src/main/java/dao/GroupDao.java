package dao;

import entities.Alias;
import entities.Group;
import entities.User;
import enums.Roles;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import service.GroupService;
import util.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.logging.Logger;

public class GroupDao {
    private static Logger log = Logger.getLogger(GroupDao.class.getName());

    public Group findGroupById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Group.class, id);
    }

    public void save(Group group) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(group);
        tx1.commit();
        session.close();
    }

    public void update(Group group) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(group);
        tx1.commit();
        session.close();
    }

    public void delete(Group group) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(group);
        tx1.commit();
        session.close();
    }

    public void deleteUserFromGroup(Group group, User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Group groupWithUser = findUserInGroup(session, user, group);
        if (groupWithUser == null) {
            session.close();
            return;
        }
        session.delete(groupWithUser);
        tx1.commit();
        session.close();
    }

    public Roles findRoleForGroupByUserId(User user, Group group) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Group groupWithUser = findUserInGroup(session, user, group);
        session.close();
        if (groupWithUser == null) {
            return null;
        }
        return groupWithUser.getRole();
    }

    private Group findUserInGroup(Session session, User user, Group group) {
        Criteria criteria = session.createCriteria(Group.class);
        List<Group> groups = criteria.add(Restrictions.eq("id", group.getId()))
                .add(Restrictions.eq("user_id", user.getId()))
                .list();
        if (groups == null || groups.size() == 0) {
            session.close();
            log.info("user is not a member of the group!");
            return null;
        }
        return groups.get(0);
    }
}
