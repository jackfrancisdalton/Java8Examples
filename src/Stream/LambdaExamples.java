package Stream;

import ResourceObjects.Artist;
import ResourceObjects.Genre;

import java.time.LocalDate;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * Created by Jack F. Dalton on 0018 18 09 2016.
 */
public class LambdaExamples {

    public static void run() {

        //region Supplier =======================================================

        /*
         * A Supplier takes no argument and returns a result of a specified value.
         * It can return null values if that is desired
         *
         * Supplier<T> : T is the return type the Supplier supplies
         * BooleanSupplier : Takes no type as it will always return a boolean value
         */

        String userName = "Donald Trump";
        boolean isLoggedOn = true;

        Supplier<String> getUsername = () -> userName;

        BooleanSupplier isUserLoggedOn = () -> isLoggedOn;

        DoubleSupplier getPi = () -> Math.PI;

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

        Consumer<String> printString = (String value) ->
            System.out.print(value.toString() + "\n");


        BiConsumer<String, LocalDate> printNameAndDOB = (String name, LocalDate dob) ->
            System.out.print("name: " + name + ", dob: " + dob.toString());

        //endregion

        //region Functions =======================================================

        /*
         * Represents a function that accepts one argument and produces a result.
         *
         * Function<R, T>
         * - R represents the expected return type
         * - T represents the the type passed to the function
         *
         * Methods:
         * - apply : applies the function to supplied arguments
         * - andThen : allows the chaining of multiple functions
         */

        Function<Artist, String> getInitials = (Artist artist) -> {
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
         * Operators are specialized Functions. Operators take T and return T.
         * Functions are not constrained in this way and can take T and return U.
         * Operators inherit all methods from the Function interface, then adds :
         * - identity() : This method returns unary operator that always returns its input argument.
         *
         * BinaryOperator contains two additional methods :
         *  - maxBy() : returns the larger argument given the Comparator argument
         *  - minBy() : returns the smaller argument given the Comparator argument
         */

        UnaryOperator<String> toChinaLocale = (s) -> s.toUpperCase(Locale.CHINA);
        UnaryOperator<String> stripWhiteSpace = (s) -> s.replace(" ", "");
        UnaryOperator<String> returnString = UnaryOperator.identity();

        BinaryOperator<String> joinWords = (String n1, String n2) -> n1 + n2;
        BinaryOperator<String> smallestString = BinaryOperator.minBy(String::compareTo);
        BinaryOperator<String> biggestString = BinaryOperator.maxBy(String::compareTo);

        //endregion ===============================================================

        //region Predicates =======================================================

        /*
         *  Predicate<T> take input of type T and returns a Boolean result.
          * Essentially used as decision for some type T.
          *
          * Predicates offer the following methods:
          * - test(<T> T) : Runs the predicate on given arguments
          * - and(Predicate<? super T> t) : Enables the chaining of predicates with an and clause
          * - or(Predicate<? super T> t) : Enables the chaining of predicates with an and clause
          * - negate() : Reverses the boolean result of the predicate
         */

        //Checks if the artists name is longer than 4
        Predicate<Artist> nameLongEnough = (Artist a) -> a.getName().length() > 14 ? true : false;

        //Checks if the artist is older than a passed date
        BiPredicate<Artist, LocalDate> isOlderThan = (Artist a, LocalDate date) ->
                a.getDateOfBirth().isBefore(date);

        //endregion

        //region Comparator
        /*
         * Comparator has existed before java 8 but we can now take advantage
         * of it with lambda and streams. In most cases they will be used in tandom
         * with the .sorted option
         *
         * Methods :
         * - reverse :
         * - compare :
         * - thenCompare :
         */

        Comparator<Artist> byArtistAge = (a1, a2) ->
                a1.getDateOfBirth().compareTo(a2.getDateOfBirth());

        Comparator<Artist> byArtistName = (a1, a2) ->
                a1.getName().compareTo(a2.getName());

        //endregion

        //region Implementation =======================================================

        List<Artist> list = Arrays.asList(

            //General Data
            new Artist("Tim McGraw", Genre.COUNTRY, LocalDate.of(1967, 5, 1)),
            new Artist("Bob Dylan", Genre.COUNTRY, LocalDate.of(1941, 5, 24)),
            new Artist("Frank Zappa", Genre.PROGROCK, LocalDate.of(1940, 12, 4)),
            new Artist("Jimi Hendrix", Genre.ROCK, LocalDate.of(1942, 11, 27)),
            new Artist("Robert Plant", Genre.ROCK, LocalDate.of(1948, 8, 20)),
            new Artist("James Blake", Genre.ELECTRO, LocalDate.of(1988, 9, 26)),
            new Artist("Elton John", Genre.POP, LocalDate.of(1947, 3, 25)),
            new Artist("Aretha Franklin", Genre.JAZZ, LocalDate.of(1942, 3, 25)),
            new Artist("Beyoncé Knowles-Carter", Genre.POP, LocalDate.of(1981, 9, 4)),

            //Test Data
            new Artist("Young Jazz B", Genre.JAZZ, LocalDate.of(1992, 3, 25)),
            new Artist("Young Jazz C", Genre.JAZZ, LocalDate.of(1992, 3, 25)),
            new Artist("Young Jazz A", Genre.JAZZ, LocalDate.of(1992, 3, 25)),
            new Artist("Young Country Artist", Genre.COUNTRY, LocalDate.of(1981, 5, 24)),
            new Artist("ZZZZZZZZ high value name", Genre.POP, LocalDate.of(1999, 9, 9))
        );

        printHeading("Artists With Long Names");
        list.stream().filter(nameLongEnough)
                .map(Artist::getName)
                .forEach(printString);


        printHeading("Artist Initials");
        list.stream().map(getInitials)
                .forEach(printString);


        printHeading("Concatenation of all artist names");
        String result = list.stream()
                .map(Artist::getName)
                .map(stripWhiteSpace)
                .reduce("", joinWords);
        System.out.println(result);


        printHeading("Artists Older than /1/6/1976");
        list.stream().filter(val -> isOlderThan.test(val, LocalDate.of(1976, 6, 1)))
                .map(Artist::getName)
                .map(toChinaLocale)
                .forEach(printString);


        printHeading("Artist by year of birth then name (youngest to oldest");
        list.stream()
                .sorted(byArtistAge.reversed().thenComparing(byArtistName))
                .map(Artist::getName)
                .forEach(printString);


        printHeading("Artists older than 1966 names changed to TOO OLD");
        List<Artist> listCopy = new ArrayList<>();
        list.forEach((Artist a) -> listCopy.add(a.clone()));
        listCopy.stream().map(artist -> {
            if(isOlderThan.test(artist, LocalDate.of(1966, 6, 1)))
                artist.setName("TOO OLD");
            return artist.getName();
        }).forEach(printString);


        printHeading("Print the smallest lexicographic name");;
        list.stream().map(Artist::getName)
                .reduce(smallestString)
                .ifPresent((String s) -> System.out.println("Smallest: " + s));

        list.stream().map(Artist::getName)
                .reduce(biggestString)
                .ifPresent((String s) -> System.out.println("Largest: " + s));


        printHeading("Collect the youngest artist in each genre");
        Map<Genre, Optional<Artist>> youngestArtistByGenre =
                list.stream()
                        .collect(
                            Collectors.groupingBy(
                                Artist::getGenre,
                                Collectors.minBy(byArtistAge)
                            )
                        );
        System.out.println("optional map: " + youngestArtistByGenre);

        List<String> youngestArtistNames = new ArrayList<>();
        youngestArtistByGenre.forEach(
                (g, a) -> a.ifPresent(
                        (artist) ->  youngestArtistNames.add(artist.getName())
                )
        );
        System.out.println("Names: " + youngestArtistNames);

        //endregion
    }

    private static void printHeading(String heading) {
        System.out.println("\n" + "=======" + heading + "=======");
    }
}
