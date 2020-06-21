package settings;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue("App")
public class AppSettings extends AbstractSettings {

    @Column(name = "os_name")
    private String osName;

    @Column(name = "memory_size")
    private Long memorySize;

    @Override
    public String toString() {
        return "AppSettings{" +
                "osName='" + osName + '\'' +
                ", memorySize=" + memorySize +
                '}';
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public Long getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(Long memorySize) {
        this.memorySize = memorySize;
    }
}
