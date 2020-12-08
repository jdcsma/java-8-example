package jun.java8.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Application {

    private static final Logger logger = LogManager.getLogger();

    /**
     * <p
     * A Stream in Java 8 can be defined as a sequence of elements from a source.
     * Streams supports aggregate operations on the elements. The source of elements
     * here refers to a Collection or Array that provides data to the Stream.
     * </p>
     * <p>
     * Stream keeps the ordering of the elements the same as the ordering in the source.
     * The aggregate operations are operations that allow us to express common manipulations
     * on stream elements quickly and clearly.
     * </p>
     * <p>
     * In Java, java.util.Stream represents a stream on which one or more operations can be performed.
     * Stream operations are either intermediate or terminal.
     * </p>
     * <ol>
     *     <li>terminal operations - return a result of a certain type</li>
     *     <li>intermediate operations -  return the stream itself so we can chain multiple methods
     *     in a row to perform the operation in multiple steps.</li>
     * </ol>
     * <p>
     * Streams are created on a source, e.g. a java.util.Collection like List or Set.
     * The Map is not supported directly, we can create stream of map keys, values or entries.
     * </p>
     */
    public static void main(String[] args) {
        stream_creation();
        stream_generation();
        stream_filter();
        stream_map();
        stream_sorted();
        stream_concat();
        stream_collect();
        stream_reduce();
        stream_match();
        stream_count();
    }

    private static void stream_creation() {
        logger.info("stream creation: -------------------");
        logger.info("create a stream from the array:");
        Stream<Integer> stream1 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        stream1.forEach(logger::info);
        logger.info("create a stream from list:");
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Stream<Integer> stream2 = list.stream();
        stream2.forEach(logger::info);
    }

    private static void stream_generation() {
        logger.info("stream generation: -------------------");
        logger.info("create a random stream:");
        Stream<Integer> stream = Stream.generate(
                () -> (new Random()).nextInt(100));
        stream.limit(10).forEach(logger::info);
    }

    /**
     * The filter() method accepts a Predicate to filter all elements of the stream.
     * This operation is intermediate which enables us to call another stream operation
     * (e.g. forEach()) on the result.
     * <p>
     * Intermediate Operations
     * </p>
     */
    private static void stream_filter() {
        logger.info("stream filter: -------------------");
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        // 查找 n % 2 == 的值，最多找到 5 结果。
        Stream<Integer> stream2 = stream.filter(n -> n % 2 == 0);
        Stream<Integer> stream3 = stream2.limit(5);
        // 遍历流中的前 5 个元素，找到 n % 2 == 0 的值。
//        Stream<Integer> stream2 = stream.limit(5);
//        Stream<Integer> stream3 = stream2.filter(n -> n % 2 == 0);
        List<Integer> result = stream3.collect(Collectors.toList());
        result.forEach(logger::info);

        // ERROR: Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
//        Integer[] number = stream3.toArray(Integer[]::new);
//        Arrays.asList(number).forEach(logger::info);
    }

    /**
     * The map() intermediate operation converts each element in the stream into
     * another object via the given function.
     * <p>
     * Intermediate Operations
     * </p>
     */
    private static void stream_map() {
        logger.info("stream map: -------------------");
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Stream.of(stream.map(n -> n + 10).toArray(Integer[]::new)).
                forEach(logger::info);
    }

    /**
     * The sorted() method is an intermediate operation that returns a sorted view of the stream.
     * The elements in the stream are sorted in natural order unless we pass a custom Comparator.
     * <p>
     * Intermediate Operations
     * </p>
     */
    private static void stream_sorted() {
        logger.info("stream sorted: -------------------");

        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        stream.sorted(Comparator.reverseOrder()).forEach(logger::info);
    }

    /**
     * Stream.concat() method is used to merge two streams into one stream which consist of
     * all elements of both merged streams.
     *
     * <ol>
     *     <li>This method creates a lazily concatenated stream whose elements are all the elements
     *         of the firstStream followed by all the elements of the secondStream.</li>
     *     <li>The resulting stream is ordered if both of the input streams are ordered.</li>
     *     <li>The resulting stream is parallel if either of the input streams is parallel.</li>
     *     <li>When the resulting stream is closed, the close handlers for both input streams are invoked.</li>
     * </ol>
     *
     * <p>
     * Intermediate Operations
     * </p>
     */
    private static void stream_concat() {
        logger.info("stream concat: -------------------");
        Stream<Integer> firstStream = Stream.of(1, 2, 3);
        Stream<Integer> secondStream = Stream.of(4, 5, 6);
        Stream<Integer> resultingStream = Stream.concat(firstStream, secondStream);
        logger.info(resultingStream.collect(Collectors.toList()));
    }

    /**
     * The collect() method is used to receive elements from a steam and store them
     * in a collection.
     * <p>
     * Terminal operations
     * </p>
     */
    private static void stream_collect() {
        logger.info("stream collect: -------------------");

        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> list = stream.collect(Collectors.toList());
        list.forEach(logger::info);

        List<String> strings = Arrays.asList("A", "B", "C");

        Stream<String> stream2 = strings.stream();
        logger.info(stream2.collect(Collectors.joining()));

        Stream<String> stream3 = strings.stream();
        logger.info(stream3.collect(Collectors.joining(",")));

        Stream<String> stream4 = strings.stream();
        logger.info(stream4.collect(Collectors.joining(",", "{", "}")));
    }

    /**
     * The reduce() method performs a reduction on the elements of the stream with the given function.
     * The result is an Optional holding the reduced value.
     * <p>
     * Terminal operations
     * </p>
     */
    private static void stream_reduce() {
        logger.info("stream reduce: -------------------");
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Optional<Integer> result = stream.reduce(Integer::sum);
        result.ifPresent(logger::info);

        Stream<String> stream2 = Stream.of("A", "B", "C");
        String reduced = stream2.reduce("-- ", (x, y) -> x + y);
        logger.info(reduced);
    }

    /**
     * The reduce() method performs a reduction on the elements of the stream with the given function.
     * The result is an Optional holding the reduced value.
     * <p>
     * Terminal operations
     * </p>
     */
    private static void stream_match() {
        logger.info("stream match: -------------------");
        List<String> names = Arrays.asList("Amitabh", "Aman");
        logger.info(names.stream().anyMatch(s -> s.startsWith("X"))); // result: false
        logger.info(names.stream().allMatch(s -> s.startsWith("A"))); // result: true
        logger.info(names.stream().noneMatch(s -> s.startsWith("X"))); // result: true
    }

    /**
     * Java Stream count() method returns the count of elements in the stream.
     * To count the number of elements in stream, we can use Collectors.counting() method as well.
     * <p>
     * Terminal operations
     * </p>
     */
    private static void stream_count() {
        logger.info("stream count: -------------------");
        List<String> names = Arrays.asList("Amitabh", "Aman", "Smith");
        logger.info(names.stream().count());
        logger.info(names.stream().filter(x -> x.startsWith("A")).count());
    }
}
