package entities;

import enums.Consumers;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(targetEntity=User.class, fetch=FetchType.EAGER)
    private User user;

    @Column(name = "text")
    private String text;

    @OneToOne(targetEntity=Alias.class, fetch=FetchType.EAGER)
    private Alias alias;

    @Column(name = "consumer")
    @Enumerated(EnumType.STRING)
    private Consumers consumer;

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Alias getAlias() {
        return alias;
    }

    public void setAlias(Alias alias) {
        this.alias = alias;
    }

    public Consumers getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumers consumer) {
        this.consumer = consumer;
    }
}
