package old;

import java.util.ArrayList;
import java.util.Hashtable;

public class City implements Comparable<City> {
    ArrayList<Connection> adjacent;
    String name;
    int hashedName;

    int distanceFromSource;
    public City(String name){
        this.name = name;
        this.hashedName = Helper.hash(name);

        adjacent = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.name).append("\n");
        for (Connection con : adjacent)
            if(con != null)
            s.append(" -> ").append(con.destination.name).append(" - ").append(con.minutes).append(" min \n");
        return s.toString();
    }

    @Override
    public int compareTo(City city) {
        return city.name.compareTo(this.name);

    }
}
