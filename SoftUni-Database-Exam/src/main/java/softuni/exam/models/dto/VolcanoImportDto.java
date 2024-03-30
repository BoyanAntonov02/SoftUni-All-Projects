package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import softuni.exam.models.entity.VolcanoType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

public class VolcanoImportDto implements Serializable {

    @Size(min = 2, max = 30)
    @Expose
    private String name;

    @Positive
    @Expose
    private int elevation;

    @Expose
    private VolcanoType volcanoType;

    @Expose
    private boolean isActive;

    @Expose
    private String lastEruption;

    @Expose
    private long country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public VolcanoType getVolcanoType() {
        return volcanoType;
    }

    public void setVolcanoType(VolcanoType volcanoType) {
        this.volcanoType = volcanoType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getLastEruption() {
        return lastEruption;
    }

    public void setLastEruption(String lastEruption) {
        this.lastEruption = lastEruption;
    }

    public long getCountry() {
        return country;
    }

    public void setCountry(long country) {
        this.country = country;
    }
}
