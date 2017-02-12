package Optional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalExamples {

    public void run() {

        List<Worker> list = new ArrayList<>(
                Arrays.asList(
                        new Worker("A"),
                        new Worker("B"),
                        new Worker("C"),
                        new Worker("D"),
                        new Worker(null),
                        new Worker("E"),
                        new Worker("F")
                )
        );

        //Get the first element and print it if it isn't empty/null
        list.get(0).getValue().ifPresent(System.out::print);

        //Note that you must ensure the optional is present before calling get
        list.stream().map(Worker::getValue)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(System.out::println);
    }

    private class Worker {
        private String value;

        public Worker(String value) {
            this.value = value;
        }

        public Optional<String> getValue() {
            return value == null ? Optional.empty() : Optional.of(value);
        }
    }
}

