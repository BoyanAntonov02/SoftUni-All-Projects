package harpoonDiver.core;

import harpoonDiver.common.ConstantMessages;
import harpoonDiver.common.ExceptionMessages;
import harpoonDiver.models.diver.*;
import harpoonDiver.models.diving.Diving;
import harpoonDiver.models.divingSite.DivingSite;
import harpoonDiver.models.divingSite.DivingSiteImpl;
import harpoonDiver.repositories.DiverRepository;
import harpoonDiver.repositories.DivingSiteRepository;
import harpoonDiver.repositories.Repository;

import java.sql.Driver;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller{

    private DiverRepository diverRepository;
    private DivingSiteRepository divingSiteRepository;

    public ControllerImpl() {
        this.diverRepository = new DiverRepository();
        this.divingSiteRepository = new DivingSiteRepository();
    }

    @Override
    public String addDiver(String kind, String diverName) {
        Diver diver = null;
        switch (kind) {
            case "OpenWaterDiver":
                diver = new OpenWaterDiver(diverName);
                break;
            case "DeepWaterDiver":
                diver = new DeepWaterDiver(diverName);
                break;
            case "WreckDiver":
                diver = new WreckDiver(diverName);
                break;
            default:
                throw new IllegalArgumentException(String.format(ExceptionMessages.DIVER_INVALID_KIND, kind));
        }
        diverRepository.add(diver);
        return String.format(ConstantMessages.DIVER_ADDED, kind, diverName);
    }

    @Override
    public String addDivingSite(String siteName, String... seaCreatures) {

        DivingSite newDivingSite = new DivingSiteImpl(siteName);

        for (String creature : seaCreatures){
            newDivingSite.getSeaCreatures().add(creature);
        }

        divingSiteRepository.add(newDivingSite);
        return String.format(ConstantMessages.DIVING_SITE_ADDED, siteName);
    }

    @Override
    public String removeDiver(String diverName) {
        Diver diver = diverRepository.byName(diverName);
        if (diver == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DIVER_DOES_NOT_EXIST, diverName));
        }
        diverRepository.remove(diver);
        return String.format(ConstantMessages.DIVER_REMOVE, diverName);
    }

    @Override
    public String startDiving(String siteName) {
        DivingSite divingSite = divingSiteRepository.byName(siteName);

        Collection<Diver> eligibleDivers = diverRepository.getCollection().stream()
                .filter(diver -> diver.getOxygen() > 30)
                .collect(Collectors.toList());

        if (eligibleDivers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.SITE_DIVERS_DOES_NOT_EXISTS);
        }


        Collection<Diver> removedDivers = diverRepository.getCollection().stream()
                .filter(diver -> diver.getOxygen() <= 30)
                .collect(Collectors.toList());

        int removedDiverCount = removedDivers.size();



        return String.format(ConstantMessages.SITE_DIVING, siteName, removedDiverCount);
    }

    @Override
    public String getStatistics() {
        StringBuilder builder = new StringBuilder();


        builder.append(String.format(ConstantMessages.FINAL_DIVING_SITES, divingSiteRepository.getCollection().size()))
                .append(System.lineSeparator())
                .append(ConstantMessages.FINAL_DIVERS_STATISTICS)
                .append(System.lineSeparator());

        for (Diver diver : diverRepository.getCollection()) {
            builder.append(String.format(ConstantMessages.FINAL_DIVER_NAME, diver.getName()))
                    .append(System.lineSeparator());

            builder.append(String.format(ConstantMessages.FINAL_DIVER_OXYGEN, diver.getOxygen()))
                    .append(System.lineSeparator());

            Collection<String> catchCollection = diver.getSeaCatch().getSeaCreatures();
            if (catchCollection.isEmpty()) {
                builder.append(String.format(ConstantMessages.FINAL_DIVER_CATCH, "None"))
                        .append(System.lineSeparator());
            } else {
                String catchString = String.join(ConstantMessages.FINAL_DIVER_CATCH_DELIMITER, catchCollection);
                builder.append(String.format(ConstantMessages.FINAL_DIVER_CATCH, catchString))
                        .append(System.lineSeparator());
            }
        }

        return builder.toString().trim();
    }
}
