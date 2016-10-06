package ParallelExample;

import ResourceObjects.Artist;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Jack F. Dalton on 0022 22 09 2016.
 */
public class ParallelHandler {

    public static void main(String[] args) {
        sorting();
    }

    public static void sorting() {

        List<String> artistList = new ArrayList<>();

        for (int i = 0; i < 50000; i++)
            artistList.add(UUID.randomUUID().toString());

        long t1s = System.nanoTime();
        artistList.stream().sorted((s1, s2) -> s1.compareTo(s2));
        long t1d = System.nanoTime() - t1s;

        long t2s = System.nanoTime();
        artistList.parallelStream().sorted((s1, s2) -> s1.compareTo(s2));
        long t2d = System.nanoTime() - t2s;

        System.out.println("Single processing time : " + t1d);
        System.out.println("Parallel processing time : "  + t2d);
    }
}
