package jun.java8.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.*;

public class Application {

    private static final Logger logger = LogManager.getLogger();

    /**
     * Java a boxed stream is a stream of the wrapper class instances to
     * simulate a stream of primitives.
     */
    public static void main(String[] args) {
        boxedStream();
        intStream();
        longStream();
        doubleStream();
    }

    private static void boxedStream() {

        logger.info("boxed stream: -------------------");

        // In Java 8, if we want to convert a stream of objects to a collection then
        // we can use one of the static methods in the Collectors class.
        List<String> strings = Stream.of("how", "to", "do", "in", "java")
                .collect(Collectors.toList());
        logger.info("convert a stream of objects to a collection:{}", strings);

        // However, the same process doesn’t work on streams of primitives.
        // Compilation Error !!
        // IntStream.of(1,2,3,4,5).collect(Collectors.toList());

        // To convert a stream of primitives, we must first box the elements in their
        // wrapper classes and then collect the wrapped objects in a collection.
        // This type of stream is called boxed stream.
        List<Integer> integers = Stream.of(1, 2, 3, 4, 5) // step 1: box the elements in their wrapper classes
                .collect(Collectors.toList()); // step 2: collect the wrapped objects in a collection
        logger.info("convert a stream of primitives to a collection:{}", integers);
    }

    private static void intStream() {

        logger.info("int stream: -------------------");

        // Get the collection and later convert to stream to process elements
        List<Integer> ints = IntStream.of(1, 2, 3, 4, 5)
                .boxed() // box the elements in their wrapper classes
                .collect(Collectors.toList());
        logger.info(ints);

        // Stream operations directly
        Optional<Integer> max = IntStream.of(1, 2, 3, 4, 5)
                .boxed() // box the elements in their wrapper classes
                .max(Integer::compareTo);
        logger.info(max);
    }

    private static void longStream() {

        logger.info("long stream: -------------------");

        // Get the collection and later convert to stream to process elements
        List<Long> ints = LongStream.of(1, 2, 3, 4, 5)
                .boxed() // box the elements in their wrapper classes
                .collect(Collectors.toList());
        logger.info(ints);

        // Stream operations directly
        Optional<Long> max = LongStream.of(1, 2, 3, 4, 5)
                .boxed() // box the elements in their wrapper classes
                .max(Long::compareTo);
        logger.info(max);
    }

    private static void doubleStream() {

        logger.info("double stream: -------------------");

        // Get the collection and later convert to stream to process elements
        List<Double> ints = DoubleStream.of(1, 2, 3, 4, 5)
                .boxed() // box the elements in their wrapper classes
                .collect(Collectors.toList());
        logger.info(ints);

        // Stream operations directly
        Optional<Double> max = DoubleStream.of(1, 2, 3, 4, 5)
                .boxed() // box the elements in their wrapper classes
                .max(Double::compareTo);
        logger.info(max);
    }
}
