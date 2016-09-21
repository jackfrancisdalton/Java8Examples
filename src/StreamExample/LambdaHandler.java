package StreamExample;

import ResourceObjects.Artist;
import ResourceObjects.Genre;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * Created by Jack F. Dalton on 0018 18 09 2016.
 */
public class LambdaHandler {

    @FunctionalInterface
    public interface ILargestWord{
        public String add(String name1, String name2, String name3);
    }

    public static void run() {

        //region Supplier =======================================================

        /*
         * A Supplier takes no argument and returns a result of a specified value.
         * It can return null values if that is desired
         *
         * Supplier<T> : T is the return type the Supplier supplies
         * BooleanSupplier : Takes no type as it will always return a boolean value
         */

        Supplier<String> surnameSupplier = () -> {
            return " Trump";
        };

        BooleanSupplier booleanSupplier = () -> true;

        //endregion

        //region Consumer ================================================

        /*
         * A Consumer is an operation that accepts an input argument and returns no result.
         * The interface has two methods :
         * - accept(T t) : runs a method implementation on the passed argument (no default)
         * - andThen(Consumer<? super T> after) : calls another consumer of the same type after finishing (has default)
         *
         * Consumer<T> : T is a value that is consumed
         * BiConsumer<T, U> : T and U are both values that are consumed
         */

        Consumer<String> consumerPrintString = (String name) -> {
            System.out.print(name.toString() + "\n");
        };


        BiConsumer<String, String> accumulaterBiconsume = (String s1, String s2) -> {

        };

        //endregion

        //region Functions =======================================================

        /*
         *
         */

        Function<Artist, String> getInitialsFunction = (Artist artist) -> {
            String[] wordsInName = artist.getName().split(" ");
            List<Character> initials = new ArrayList<>();

            for (String word : wordsInName)
                initials.add(word.charAt(0));

            return initials.toString();
        };

        BiFunction<String,String, String> bifunction;

        //endregion ===============================================================

        //region Operator =======================================================

        /*
         * Operators are specialized Functions. Operators take T return T.
         * Functions are not constrained in this way and can take T and return U.
         * As such it inherits all methods from the Function interface, it also adds :
         * - identity() : This method returns unary operator that always returns its input argument.
         *
         * BinaryOperator contains two additional methods :
         *  - maxBy() : returns the larger argument given the Comparator argument
         *  - minBy() : returns the smaller argument given the Comparator argument
         *
         */

        UnaryOperator<String> unaryOperator = (s1) -> {
            return "";
        };

        BinaryOperator<String> binaryOperator = (String n1, String n2) -> {
            return "";
        };

        //endregion ===============================================================

        //region Predicates =======================================================

        /*
         *  Predicate<T> take input of type T and returns a Boolean result.
          * Essentially used as decision for some type T.
          * - test
          * - and
          * - or
          * - negate
         */

        Predicate<Artist> predicateLongName = (Artist a) -> {
            if(a.getName().length() > 4) {
                return true;
            } else {
                return false;
            }
        };

        BiPredicate<String, String> biPredicate;

        //endregion

        List<Artist> list = Arrays.asList(
                new Artist("Tim McGraw", Genre.COUNTRY, LocalDate.of(1967, 5, 1)),
                new Artist("Bob Dylan", Genre.COUNTRY, LocalDate.of(1941, 5, 24)),
                new Artist("Frank Zappa", Genre.PROGROCK, LocalDate.of(1940, 12, 4)),
                new Artist("Jimi Hendrix", Genre.ROCK, LocalDate.of(1942, 11, 27)),
                new Artist("Robert Plant", Genre.ROCK, LocalDate.of(1948, 8, 20)),
                new Artist("James Blake", Genre.ELECTRO, LocalDate.of(1988, 9, 26)),
                new Artist("Elton John", Genre.POP, LocalDate.of(1947, 3, 25)),
                new Artist("Aretha Franklin", Genre.JAZZ, LocalDate.of(1942, 3, 25)),
                new Artist("Beyoncé Knowles-Carter", Genre.POP, LocalDate.of(1981, 9, 4))
        );


        //print out names of people with long names
        list.stream().filter(predicateLongName)
                .map(Artist::toString)
                .forEach(consumerPrintString);

        //map to initials of artists and print each one
        list.stream().map(getInitialsFunction)
                .forEach(consumerPrintString);

        List<String> namesOfAritsts = list.stream()
                .map(Artist::getName)
                .collect(Collectors.toList());

        String result = list.stream().map(Artist::getName).reduce("", (artist1Name, artist2Name) -> {
            return artist1Name + artist2Name;
        });

        System.out.print(result);
    }
}
