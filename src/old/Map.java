package old;

import javax.xml.namespace.QName;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Map {
    City[] cities;
    PriorityQueue<QueueEntry> pq;
    Hashtable<String, City> settledNodes;
    public  List<QueueEntry> dijkstrasList(City source){
        settledNodes = new Hashtable<>();
        pq.add(new QueueEntry(source, 0, null));
        QueueEntry temp = pq.peek();
        QueueEntry currentLook;
        List<QueueEntry> returns = new ArrayList<>();


        while(!pq.isEmpty()){
            temp = pq.peek();
            settledNodes.put(temp.thisCity.name, temp.thisCity);
            QueueEntry q = pq.poll();
            boolean truthiness = false;
            for(int w = 0; w < returns.size(); w++){
                QueueEntry temporary;
                if((temporary = returns.get(w)).thisCity.name.equals(q.thisCity.name)){
                    truthiness = true;
                    if(q.distanceFromSource < temporary.distanceFromSource){
                        returns.set(w, q);
                    }
                }
            }
            if(!truthiness)
                returns.add(q);


            for(Connection c : temp.thisCity.adjacent){
                City city = c.destination;
                boolean truth = false;
                if(settledNodes.contains(c.destination)){
                    City cit1 = settledNodes.get(c.destination.name);
                    if(cit1.distanceFromSource > c.minutes + temp.distanceFromSource){
                        cit1.distanceFromSource = c.minutes + temp.distanceFromSource;
                    }
                }
                else
                {
                    pq.add(new QueueEntry(c.destination, c.minutes+temp.distanceFromSource, temp.thisCity));
                }

            }

        }
        return returns;
    }
    public Integer dijkstras2(City source, City targetCity){
        List<QueueEntry> qe = dijkstrasList(source);
        for (QueueEntry q :
                qe) {
            if (targetCity.name.equals(q.thisCity.name)) {
                return q.distanceFromSource;
            }
        }
        return null;
    }
    public  Integer dijkstras(City source, City targetCity){
        settledNodes = new Hashtable<>();
        pq.add(new QueueEntry(source, 0, null));
        QueueEntry temp = pq.peek();
        QueueEntry currentLook;


        while(!Objects.equals((Objects.requireNonNull(currentLook = pq.peek())).thisCity.name, targetCity.name)){
            temp = pq.peek();
            settledNodes.put(temp.thisCity.name, temp.thisCity);
            pq.poll();

            for(Connection c : temp.thisCity.adjacent){
                City city = c.destination;
                boolean truth = false;
                if(settledNodes.contains(c.destination)){
                    City cit1 = settledNodes.get(c.destination.name);
                    if(cit1.distanceFromSource > c.minutes + temp.distanceFromSource){
                        cit1.distanceFromSource = c.minutes + temp.distanceFromSource;
                    }
                }
                else
                {
                    pq.add(new QueueEntry(c.destination, c.minutes+temp.distanceFromSource, temp.thisCity));
                }

            }

        }
        temp = pq.poll();
        return temp.distanceFromSource;
    }


    public Map(String file, int maxNodes) {
        settledNodes = new Hashtable<>();

        pq = new PriorityQueue<>(5, new QueueComparator());
         int i= 0;
        cities = new City[541];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null && i++ < maxNodes) {
                line = line.replaceAll(",", " ");
                String[] array = line.split(" ");
                this.addLine(array[0],array[1], Integer.parseInt(array[2]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addLine(String s){
        String[] temp = s.replaceAll(",", " ").split(",");
        addLine(temp[0],temp[1],Integer.parseInt(temp[2]));
    }

    public void addLine(String t1, String t2, int distance){
        //city 1
        int hashed1 = Helper.hash(t1);
        City location;
        location = findOrAddCity(t1, hashed1);
        //city 2
        int hashed2 = Helper.hash(t2);
        City location2;
        location2 = findOrAddCity(t2, hashed2);
        location.adjacent.add(new Connection(location2, distance));
        location2.adjacent.add(new Connection(location, distance));

    }
    private City findOrAddCity(String name, int hashed){
        City location = null;
        if(cities[hashed] == null){
            location = new City(name);
            cities[hashed] = location;
        }
        else {
            City c = lookup(hashed);
            if (name.equals(c.name)) {
                location = c;
            } else System.out.println("Collision! at " + name + " " + hashed);
        }
        return location;
    }
    public City lookup(int hash){return cities[hash];}
    public City lookup(String s){ return cities[Helper.hash(s)];}

}
