package entities;

import enums.Roles;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "app_groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(targetEntity=Alias.class, fetch=FetchType.EAGER, cascade = { CascadeType.ALL })
    private Alias alias;//alias for group

    @ManyToOne(targetEntity=User.class, fetch=FetchType.EAGER)//user-admin
    private User user;//members(both users and admins)

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Roles role;

    public int getId() {
        return id;
    }

    public Alias getAlias() {
        return alias;
    }

    public void setAlias(Alias alias) {
        this.alias = alias;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
