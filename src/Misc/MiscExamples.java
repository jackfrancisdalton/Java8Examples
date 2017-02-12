package Misc;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class MiscExamples {

    public static void StringJoiner() {

        List<String> words = Arrays.asList("Chicken", "Beef", "Lobster");
        StringJoiner sj = new StringJoiner(",", "(", ")");

        words.forEach(sj::add);
        System.out.print(words);
    }

    public static void FileReader() {

    }
}
