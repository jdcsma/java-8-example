package jun.java8.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;


public class Application {

    private static final Logger logger = LogManager.getLogger();

    /**
     * The Java forEach() method is a utility function to iterate over a collection
     * such as (list, set or map) and stream. It is used to perform a given action
     * on each the element of the collection.
     * <p>
     * The forEach() method has been added in following places:
     * <p>
     * <ul>
     *     <li>Iterable interface – This makes Iterable.forEach() method available to all collection classes except Map</li>
     *     <li>Map interface – This makes forEach() operation available to all map classes.</li>
     *     <li>Stream interface – This makes forEach() and forEachOrdered() operations available to all types of stream.</li>
     * </ul>
     */
    public static void main(String[] args) {
        forEach_iterable();
        forEach_map();
        forEach_stream();
        forEachOrdered_stream();
    }

    private static void forEach_iterable() {
        logger.info("forEach_iterable: -------------------");

        List<String> names = Arrays.asList("Alex", "Brian", "Charles");

        logger.info("1. forEach() method with method reference:");

        names.forEach(logger::info);

        logger.info("2. Creating custom Consumer interface:");

        Consumer<String> consumer1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                logger.info(s);
            }
        };
        names.forEach(consumer1);

        Consumer<String> consumer2 = s -> {
            logger.info(s);
        };
        names.forEach(consumer2);
    }

    private static void forEach_map() {
        logger.info("forEach_map: -------------------");

        Map<String, String> map = new HashMap<>();

        map.put("A", "Alex");
        map.put("B", "Brian");
        map.put("C", "Charles");

        logger.info("1. forEach() method with lambda expression");

        map.forEach((k, v) -> logger.info("k:{} v:{}", k, v));

        logger.info("2. Creating custom BiConsumer interface");

        BiConsumer<String, String> consumer1 = new BiConsumer<String, String>() {
            @Override
            public void accept(String k, String v) {
                logger.info("k:{} v:{}", k, v);
            }
        };
        map.forEach(consumer1);

        BiConsumer<String, String> consumer2 = (k, v) -> {
            logger.info("k:{} v:{}", k, v);
        };
        map.forEach(consumer2);
    }

    private static void forEach_stream() {
        logger.info("forEach_stream: -------------------");

        // In Stream, forEach() is terminal operation.

        // Similar to Iterable, stream forEach() method performs an action for each element of the stream.

        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5);

        logger.info("1. forEach() method with method reference:");

        numberList.stream().
                filter(n -> n % 2 == 0).
                forEach(logger::info);

    }

    private static void forEachOrdered_stream() {

        logger.info("forEachOrdered_stream: -------------------");

        // In Stream, forEachOrdered() is terminal operation.

        // While using parallel streams, use forEachOrdered() if order of the elements matter
        // during the iteration. forEach() method does not guarantee the element ordering to
        // provide the advantages of parallelism.

        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5);

        logger.info("1. forEachOrdered() method with method reference:");

        numberList.stream().
                filter(n -> n % 2 == 0).
                parallel().
                forEachOrdered(logger::info);
    }
}
