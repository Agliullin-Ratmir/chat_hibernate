package service;

import dao.AliasDao;
import dao.GroupDao;
import dao.UserDao;
import entities.Alias;
import entities.Group;
import entities.User;
import enums.Consumers;
import enums.Roles;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import java.util.Scanner;
import java.util.logging.Logger;

public class GroupService {
    private static Logger log = Logger.getLogger(GroupService.class.getName());

    private GroupDao groupDao = new GroupDao();

    public boolean isUserAdminOfGroup(User user, Group group) {
        if (Roles.ADMIN == groupDao.findRoleForGroupByUserId(getSession(), user, group)) {
            return true;
        }
        return false;
    }

    public void deleteUserFromGroup(User deleter, Group group, User deleted) {
        if (!isUserAdminOfGroup(deleter, group)) {
            log.info("you can't delete because you are not admin of this group");
            return;
        }
        if (!isUserAdminOfGroup(deleted, group)) {
            log.info("you can't delete this user because he is an admin of this group");
            return;
        }
        groupDao.deleteUserFromGroup(getSession(), group, deleted);
        log.info(String.format("User with alias=%s has been deleted from group alias=%s",
                deleted.getAlias().getTitle(), group.getAlias().getTitle()));
    }

    public void addNewGroup(User userAdmin, Alias aliasForGroup){
        Group newGroup = new Group();
        newGroup.setUser(userAdmin);
        newGroup.setAlias(aliasForGroup);
        newGroup.setRole(Roles.ADMIN);
        groupDao.save(getSession(), newGroup);
        log.info(String.format("new group has been added with alias = %s",
                aliasForGroup.getTitle()));
    }

    public void addNewUserAsUserToGroup(User newUser, Group group){
        Group newGroupRow = new Group();
        newGroupRow.setUser(newUser);
        newGroupRow.setAlias(group.getAlias());
        newGroupRow.setRole(Roles.USER);
        groupDao.save(getSession(), newGroupRow);
        log.info(String.format("new user %s has been added to group %s",
                newUser.getAlias().getTitle(), group.getAlias()));
    }

    public void addNewUserAsAdminToGroup(User newUser, Group group, User addingUser){
        if (!isUserAdminOfGroup(addingUser, group)) {
            log.info("you can't add new user as an admin because you're an admin of the group");
            return;
        }
        Group newGroupRow = new Group();
        newGroupRow.setUser(newUser);
        newGroupRow.setAlias(group.getAlias());
        newGroupRow.setRole(Roles.ADMIN);
        groupDao.save(getSession(), newGroupRow);
        log.info(String.format("new user %s has been added to group %s as an admin by %s",
                newUser.getAlias().getTitle(), group.getAlias(),
                addingUser.getAlias().getTitle()));
    }

    public void makeNewGroup() {
        Scanner scanner = new Scanner(System.in);
        Session session = getSession();
        AliasDao aliasDao = new AliasDao();
        UserDao userDao = new UserDao();
        GroupDao groupDao = new GroupDao();
        Transaction tx1 = session.beginTransaction();

        System.out.println("Please, write your alias");
        String aliasTitle = scanner.nextLine();
        User userAdmin = userDao.findUserByAlias(session, aliasTitle);
        System.out.println("Please, write alias for the group");
        String groupAliasTitle = scanner.nextLine();
        Alias groupAlias = new Alias();
        groupAlias.setTitle(groupAliasTitle);
        groupAlias.setConsumer(Consumers.GROUP);
        aliasDao.save(session, groupAlias);

        Group group = new Group();
        group.setAlias(groupAlias);
        group.setUser(userAdmin);
        group.setRole(Roles.ADMIN);
        groupDao.save(session, group);

        tx1.commit();
        session.close();
    }

    private Session getSession() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }
}
