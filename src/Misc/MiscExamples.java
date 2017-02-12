package Misc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Stream;

public class MiscExamples {

    public static void run(){
        stringJoiner();
        fileReader();
    }

    public static void stringJoiner() {
        List<String> words = Arrays.asList("Chicken", "Beef", "Lobster");
        StringJoiner sj = new StringJoiner(",", "(", ")");

        words.forEach(sj::add);
        System.out.println("StringJoiner Joined Words");
        System.out.println(words + "\n");
    }

    public static void fileReader() {

        System.out.println("FileRead Words");
        String filename = System.getProperty("user.dir") + "/src/Misc/lines.txt";

        //Will print out all of the lines other than the one containing "not a line"
        try(Stream<String> stream = Files.lines(Paths.get(filename))) {
            stream.filter((String s) -> !s.contains("not a line"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
