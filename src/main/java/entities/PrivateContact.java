package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "private_contact")
public class PrivateContact extends AbstractContact implements Serializable {

    @OneToOne(targetEntity=User.class, fetch= FetchType.LAZY)
    private User observer;

    public User getObserver() {
        return observer;
    }

    public void setObserver(User observer) {
        this.observer = observer;
    }
}
