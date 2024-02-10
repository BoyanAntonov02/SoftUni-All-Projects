package halloween;

import java.util.ArrayList;
import java.util.List;

public class House {
    private int capacity;
    private List<Kid> data;

    public House(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void addKid(Kid kid){
        if(this.capacity > this.data.size()){
            this.data.add(kid);
        }
    }

    public boolean removeKid(String name){
        for(Kid k : this.data){
            if(k.getName().equals(name)){
                this.data.remove(k);
                return true;
            }
        }
        return false;
    }

    public Kid getKid(String street){
        for(Kid k : this.data){
            if(k.getStreet().equals(street)){
                return k;
            }
        }
        return null;
    }

    public int getAllKids(){
        return data.size();
    }

    public String getStatistics(){
        StringBuilder sb = new StringBuilder();
        sb.append("Children who visited a house for candy:").append(System.lineSeparator());
        this.data.forEach(e -> sb.append(e.getName()).append(" from ").append(e.getStreet()).append(" street").append(System.lineSeparator()));
        return sb.toString();
    }


}
