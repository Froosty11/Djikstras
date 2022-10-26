package old;

import java.util.Comparator;

public class QueueEntry {
    City thisCity;
    City previousCity;
    int distanceFromSource;
    public QueueEntry(City sourceName, int distanceFromSource, City origin){
        this.thisCity = sourceName;
        this.previousCity = origin;
        this.distanceFromSource = distanceFromSource;

    }

}
class QueueComparator implements Comparator<QueueEntry> {

    // Overriding compare()method of Comparator
    // for descending order of cgpa
    public int compare(QueueEntry s1, QueueEntry s2) {
        if (s1.distanceFromSource > s2.distanceFromSource)
            return 1;
        else if (s1.distanceFromSource < s2.distanceFromSource)
            return -1;
        return 0;
    }
}
