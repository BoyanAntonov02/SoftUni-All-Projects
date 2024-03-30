package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.VolcanoImportDto;
import softuni.exam.models.entity.Volcano;
import softuni.exam.models.entity.VolcanoType;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.service.VolcanoService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VolcanoServiceImpl implements VolcanoService {
    //src/main/resources/files/json/volcanoes.json
    //C:\Users\boyan\Desktop\Explore-Volcano-Skeleton\Explore-Volcano-Skeleton\src\main\resources\files\json\volcanoes.json
    private static final String FILE_PATH = "src/main/resources/files/json/volcanoes.json";

    private final VolcanoRepository volcanoRepository;
    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public VolcanoServiceImpl(VolcanoRepository volcanoRepository, CountryRepository countryRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.volcanoRepository = volcanoRepository;
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.volcanoRepository.count() > 0;
    }

    @Override
    public String readVolcanoesFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_PATH)));
    }

    @Override
    public String importVolcanoes() throws IOException {
        StringBuilder sb = new StringBuilder();
        VolcanoImportDto[] volcanoImportDtos = this.gson.fromJson(readVolcanoesFileContent(), VolcanoImportDto[].class);

        for (VolcanoImportDto volcanoImportDto : volcanoImportDtos) {
            Optional<Volcano> optional = this.volcanoRepository.findByName(volcanoImportDto.getName());
            if (!this.validationUtil.isValid(volcanoImportDto) || optional.isPresent()) {
                sb.append("Invalid volcano\n");
                continue;
            }

            Volcano volcano = this.modelMapper.map(volcanoImportDto, Volcano.class);
            volcano.setVolcanoType(VolcanoType.valueOf(String.valueOf(volcanoImportDto.getVolcanoType())));
            volcano.setCountry(this.countryRepository.getById(volcanoImportDto.getCountry()));
            this.volcanoRepository.save(volcano);
            sb.append(String.format("Successfully imported volcano %s of type %s\n", volcano.getName(), volcano.getVolcanoType()));


        }

        return sb.toString();
    }

    @Override
    public String exportVolcanoes() {

        return this.volcanoRepository
                .findAllByActiveTrueAndElevationGreaterThanOrderByElevationDesc()
                .stream()
                .map(v -> String.format("Volcano: %s\n" +
                                "   *Located in: %s\n" +
                                "   **Elevation: %d\n" +
                                "   ***Last eruption on: %s\n",
                        v.getName(), v.getCountry().getName(), v.getElevation(), v.getLastEruption())).collect(Collectors.joining());
    }
}