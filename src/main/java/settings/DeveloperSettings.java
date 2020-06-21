package settings;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue("Developer")
public class DeveloperSettings extends AbstractSettings {

    @Column(name = "name")
    private String name;

    @Email
    @Column(name = "email")
    private String email;

    @Override
    public String toString() {
        return "DeveloperSettings{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
