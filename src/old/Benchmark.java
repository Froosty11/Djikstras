package old;

import java.util.ArrayList;
import java.util.List;

public class Benchmark {
    public static void BenchmarkList(){
        Map m = new Map("src/trains.csv",1000);
        long t_Start = 0;
        int iterations = 1;
        List<QueueEntry> result = new ArrayList<>();
        long total = 0;
        City c1 = m.lookup("Malmö");
        for(int i = 0; i < iterations; i ++){
            t_Start = System.nanoTime();
            result = m.dijkstrasList(c1);
            total += System.nanoTime()-t_Start;
        }
        StringBuilder s = new StringBuilder();
        for (QueueEntry qe : result) {
            s.append(qe.thisCity.name);
            s.append(" : ");
            s.append(qe.distanceFromSource);
            s.append("\n");
        }
        System.out.printf("avg. time %d : result\n %s",total/iterations, s.toString());
    }
    public static void BenchMarkSingle(){
        Map m = new Map("src/trains.csv",1000);
        long t_Start = 0;
        int iterations = 1000;
        int result = 0;
        long total;
        String[][] towns = {{"Malmö","Göteborg"},{"Göteborg","Stockholm"},{"Malmö", "Stockholm"},{"Stockholm","Sundsvall"},{"Stockholm","Umeå"}, {"Göteborg", "Sundsvall"},{"Sundsvall", "Umeå"},{"Umeå","Göteborg"},{"Göteborg","Umeå"}};
        for (String[] s : towns) {
            City c1 = m.lookup(s[0]);
            City c2 = m.lookup(s[1]);
            total = 0;
            for (int i = 0; i < iterations; i++) {
                t_Start = System.nanoTime();
                result = m.dijkstras2(c1, c2);
                total += System.nanoTime() - t_Start;
            }
            System.out.printf("%s -> %s : avg. time %d : result %d\n", s[0], s[1], total / iterations, result);
        }
    }
}
