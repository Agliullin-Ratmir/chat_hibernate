package entities;

import enums.Consumers;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Cacheable
@Table(name = "messages")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Message  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(targetEntity=User.class, fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private User user;//sender

    @NotBlank(message = "text of message can't be blank")
    @Column(name = "text")
    private String text;

    @OneToOne(targetEntity=Alias.class, fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private Alias alias;//consumer's alias

    @Column(name = "consumer")
    @Enumerated(EnumType.STRING)
    private Consumers consumer;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", user=" + user +
                ", text='" + text + '\'' +
                ", alias=" + alias +
                ", consumer=" + consumer +
                '}';
    }

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
