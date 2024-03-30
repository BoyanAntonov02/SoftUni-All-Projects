package softuni.exam.models.dto;


import softuni.exam.util.LocalDateAdapter;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

@XmlRootElement(name = "volcanologist")
@XmlAccessorType(XmlAccessType.FIELD)
public class VolcanologistSeedDto implements Serializable {



    @XmlElement(name = "first_name")
    @Size(min = 2, max = 30)
    private String firstName;
    @XmlElement(name = "last_name")
    @Size(min = 2, max = 30)
    private String lastName;

    @XmlElement
    @NotNull
    @Positive
    private double salary;
    @XmlElement
    @Min(19)
    @Max(80)
    @NotNull
    private int age;

    @XmlElement(name = "exploring_from")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate exploringFrom;
    @XmlElement(name = "exploring_volcano_id")
    private long volcano;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getExploringFrom() {
        return exploringFrom;
    }

    public void setExploringFrom(LocalDate exploringFrom) {
        this.exploringFrom = exploringFrom;
    }

    public long getVolcano() {
        return volcano;
    }

    public void setVolcano(long volcano) {
        this.volcano = volcano;
    }
}
