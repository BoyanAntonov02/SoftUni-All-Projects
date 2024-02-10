package harpoonDiver.repositories;

import harpoonDiver.models.divingSite.DivingSite;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class DivingSiteRepository implements Repository<DivingSite> {
    private Map<String, DivingSite> sites;

    public DivingSiteRepository() {
        this.sites = new HashMap<>();
    }

    @Override
    public void add(DivingSite divingSite) {
        if (divingSite == null || sites.containsKey(divingSite.getName())) {
            return; // Skip adding if the site is null or already exists
        }
        sites.put(divingSite.getName(), divingSite);
    }

    @Override
    public boolean remove(DivingSite divingSite) {
        if (divingSite == null) {
            return false;
        }
        return sites.remove(divingSite.getName()) != null;
    }

    @Override
    public DivingSite byName(String name) {
        return sites.get(name);
    }

    @Override
    public Collection<DivingSite> getCollection() {
        return Collections.unmodifiableCollection(sites.values());

    }
}