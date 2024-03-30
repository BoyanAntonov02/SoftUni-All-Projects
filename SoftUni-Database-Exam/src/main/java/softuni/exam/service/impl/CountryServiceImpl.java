package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountryImportDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    //src/main/resources/files/json/countries.json
    //C:\Users\boyan\Desktop\Explore-Volcano-Skeleton\Explore-Volcano-Skeleton\src\main\resources\files\json\countries.json

    private static final String FILE_PATH = "src/main/resources/files/json/countries.json";
    private final CountryRepository countryRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public CountryServiceImpl(CountryRepository countryRepository, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.countryRepository = countryRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_PATH)));
    }

    @Override
    public String importCountries() throws IOException {
        StringBuilder sb = new StringBuilder();
        CountryImportDto[] countryImportDtos = this.gson.fromJson(readCountriesFromFile(), CountryImportDto[].class);

        for (CountryImportDto countryImportDto : countryImportDtos) {
            Optional<Country> optional = this.countryRepository.findByName(countryImportDto.getName());
            if (!this.validationUtil.isValid(countryImportDto) || optional.isPresent()) {
                sb.append("Invalid country\n");
                continue;
            }

            Country country = this.modelMapper.map(countryImportDto, Country.class);
            this.countryRepository.saveAndFlush(country);

            sb.append(String.format("Successfully imported country %s - %s\n", country.getName(), country.getCapital()));
        }

        return sb.toString();
    }
}
