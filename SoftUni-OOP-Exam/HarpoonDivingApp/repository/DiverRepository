package harpoonDiver.repositories;

import harpoonDiver.models.diver.BaseDiver;
import harpoonDiver.models.diver.Diver;
import harpoonDiver.repositories.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class DiverRepository implements Repository<Diver> {
    private Map<String, Diver> divers;

    public DiverRepository() {
        this.divers = new HashMap<>();
    }

    @Override
    public void add(Diver diver) {
        divers.putIfAbsent(diver.getName(), diver);
    }

    @Override
    public boolean remove(Diver diver) {
        if (diver == null) {
            return false;
        }
        return divers.remove(diver.getName()) != null;
    }

    @Override
        public Diver byName(String name) {
        return divers.get(name);
    }

    @Override
    public Collection<Diver> getCollection() {
        return Collections.unmodifiableCollection(divers.values());
    }
}
