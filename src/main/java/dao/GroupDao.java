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

    public Group findGroupById(Session session, int id) {
        return session.get(Group.class, id);
    }

    public void save(Session session, Group group) {
        session.persist(group);
    }

    public void update(Session session, Group group) {
        session.merge(group);
    }

    public void delete(Session session, Group group) {
        session.remove(group);
    }

    public void deleteUserFromGroup(Session session, Group group, User user) {
        Group groupWithUser = findUserInGroup(session, user, group);
        if (groupWithUser == null) {
            return;
        }
        session.delete(groupWithUser);
    }

    public Roles findRoleForGroupByUserId(Session session, User user, Group group) {
        Group groupWithUser = findUserInGroup(session, user, group);
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
