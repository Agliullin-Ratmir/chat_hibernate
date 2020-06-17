package service;

import dao.GroupDao;
import entities.Alias;
import entities.Group;
import entities.User;
import enums.Roles;

import java.util.logging.Logger;

public class GroupService {
    private static Logger log = Logger.getLogger(GroupService.class.getName());

    private GroupDao groupDao = new GroupDao();

    public boolean isUserAdminOfGroup(User user, Group group) {
        if (Roles.ADMIN == groupDao.findRoleForGroupByUserId(user, group)) {
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
        groupDao.deleteUserFromGroup(group, deleted);
        log.info(String.format("User with alias=%s has been deleted from group alias=%s",
                deleted.getAlias().getTitle(), group.getAlias().getTitle()));
    }


    public void addNewGroup(User userAdmin, Alias aliasForGroup){
        Group newGroup = new Group();
        newGroup.setUser(userAdmin);
        newGroup.setAlias(aliasForGroup);
        newGroup.setRole(Roles.ADMIN);
        groupDao.save(newGroup);
        log.info(String.format("new group has been added with alias = %s",
                aliasForGroup.getTitle()));
    }

    public void addNewUserAsUserToGroup(User newUser, Group group){
        Group newGroupRow = new Group();
        newGroupRow.setUser(newUser);
        newGroupRow.setAlias(group.getAlias());
        newGroupRow.setRole(Roles.USER);
        groupDao.save(newGroupRow);
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
        groupDao.save(newGroupRow);
        log.info(String.format("new user %s has been added to group %s as an admin by %s",
                newUser.getAlias().getTitle(), group.getAlias(),
                addingUser.getAlias().getTitle()));
    }
}
