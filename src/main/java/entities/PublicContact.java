package entities;

import enums.ObserverType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "public_contact")
public class PublicContact extends AbstractContact implements Serializable {

    @Column(name = "observer_type")
    private ObserverType observerType;

    public ObserverType getObserverType() {
        return observerType;
    }

    public void setObserverType(ObserverType observerType) {
        this.observerType = observerType;
    }
}
