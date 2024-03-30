package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String name;

    @Column
    private String capital;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
