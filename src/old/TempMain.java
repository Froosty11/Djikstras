package old;

import java.util.PriorityQueue;

public class TempMain {
    public static void main(String[] args) {
        PriorityQueue<QueueEntry> pq = new PriorityQueue<>(1, new QueueComparator());
        pq.add(new QueueEntry(new City("cock & balls"), 0, null));
        pq.add(new QueueEntry(new City("Tuscany"), 2, null));
        pq.add(new QueueEntry(new City("Athens"), 4, null));
        pq.add(new QueueEntry(new City("Epic poggers moments"), 6, null));
        System.out.println(pq.poll().thisCity.name);
        System.out.println(pq.poll().thisCity.name);
        System.out.println(pq.poll().thisCity.name);
        System.out.println(pq.poll().thisCity.name);


    }
}
