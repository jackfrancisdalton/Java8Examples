package Parallel;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Jack F. Dalton on 0022 22 09 2016.
 */
public class ParallelismExamples {

    public static void run() {
        concurrency();
        sorting();
    }

    public static void sorting() {

        //Create and populate a list of random words
        List<String> wordList = new ArrayList<>();
        for (int i = 0; i < 500000; i++)
            wordList.add(UUID.randomUUID().toString());

        //Sort and filter the list using compare to sequentially
        long t1s = System.nanoTime();
        List<String> a =wordList.stream()
                .filter((String s) -> s.contains("Z") ? true : false)
                .sorted((s1, s2) -> s1.compareTo(s2))
                .collect(Collectors.toList());
        long t1d = System.nanoTime() - t1s;

        //Sort and filter the list using compare to in parallel
        long t2s = System.nanoTime();
        List<String> b = wordList.parallelStream()
                .filter((String s) -> s.contains("Z") ? true : false)
                .sorted((s1, s2) -> s1.compareTo(s2))
                .collect(Collectors.toList());
        long t2d = System.nanoTime() - t2s;

        System.out.println("Filter + Sort Serial time   : " + t1d);
        System.out.println("Filter + Sort Parallel time : "  + t2d);
    }

    public static void concurrency() {

        //Create and populate a list of random words
        List<String> words = new ArrayList<>();
        for (int i = 0; i < 500000; i++)
            words.add(UUID.randomUUID().toString());

        //Groups the list into a map of strings by length in serial
        long t2s = System.nanoTime();
        Map<Integer, List<String>> a = words.stream()
                .collect(Collectors.groupingBy(String::length));
        long t2d = System.nanoTime() - t2s;

        //Groups the list into a map of strings by length in parallel
        long t1s = System.nanoTime();
        ConcurrentMap<Integer, List<String>> b = words.parallelStream()
                        .collect(Collectors.groupingByConcurrent(String::length));
        long t1d = System.nanoTime() - t1s;

        System.out.println("Collect Serial time   : " + t2d);
        System.out.println("Collect Parallel time : "  + t1d);
    }
}
