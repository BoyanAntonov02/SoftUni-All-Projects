package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "volcanologists")
@XmlAccessorType(XmlAccessType.FIELD)
public class VolcanologistsRootDto implements Serializable {

    @XmlElement(name = "volcanologist")
    private List<VolcanologistSeedDto> volcanologistsSeedDto;

    public List<VolcanologistSeedDto> getVolcanologistsSeedDto() {
        return volcanologistsSeedDto;
    }

    public void setVolcanologistsSeedDto(List<VolcanologistSeedDto> volcanologistsSeedDto) {
        this.volcanologistsSeedDto = volcanologistsSeedDto;
    }
}
