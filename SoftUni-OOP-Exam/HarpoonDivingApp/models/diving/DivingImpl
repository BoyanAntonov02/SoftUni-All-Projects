package harpoonDiver.models.diving;

import harpoonDiver.models.diver.BaseDiver;
import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.divingSite.DivingSite;

import java.util.Collection;
import java.util.Iterator;

public class DivingImpl implements Diving{
    @Override
    public void searching(DivingSite divingSite, Collection<Diver> divers) {
       for(Diver diver : divers){
           if(!diver.canDive()) continue;

           Iterator<String> it = divingSite.getSeaCreatures().iterator();
           while (diver.canDive() && it.hasNext()) {
               String creature = it.next();
               diver.shoot();
               diver.getSeaCatch().getSeaCreatures().add(creature);
               it.remove();
           }
       }
       }
    }

