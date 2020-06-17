package entities;

import enums.Roles;

import javax.persistence.*;

@Entity
@Table(name = "app_groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(targetEntity=Alias.class, fetch=FetchType.EAGER)
    private Alias alias;

    @OneToMany(targetEntity=User.class, fetch=FetchType.EAGER)
    private User user;

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
