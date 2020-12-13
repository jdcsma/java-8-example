package jun.java8.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Application {

    private static final Logger logger = LogManager.getLogger();

    /**
     * Java 8 allows four types of method references.
     * <ul>
     *     <li>Reference to static method</li>
     *     <li>Reference to instance method from instance</li>
     *     <li>Reference to instance method from class type</li>
     *     <li>Reference to constructor</li>
     * </ul>
     */
    public static void main(String[] args) {
        referenceStaticMethod();
        referenceInstanceMethodFromInstance();
        referenceInstanceMethodFromClassType();
        referenceConstructor();
    }

    /**
     * Used to refer static methods from a class
     */
    private static void referenceStaticMethod() {
        logger.info("reference to static method: -------------------");
        Optional<Integer> result = Stream.of(1, 2, 3, 4, 5).reduce(Math::max);
        logger.info(result.orElse(-1));
    }

    /**
     * Refer to an instance method using a reference to the supplied object
     */
    private static void referenceInstanceMethodFromInstance() {
        logger.info("reference to instance method from instance: -------------------");
        Stream.of(1, 2, 3, 4, 5).forEach(logger::info);
    }

    /**
     * Invoke the instance method on a reference to an object supplied by the context
     */
    private static void referenceInstanceMethodFromClassType() {
        logger.info("reference to instance method from type: -------------------");

        List<String> strings = Arrays
                .asList("how", "to", "do", "in", "java", "dot", "com");

        strings.stream()
                .sorted(String::compareTo)
                .forEach(logger::info);
    }

    /**
     * Reference to a constructor
     */
    private static void referenceConstructor() {
        logger.info("reference to constructor: -------------------");

        IntStream.range(1, 100).boxed()
                .collect(Collectors.toCollection(ArrayList::new))
                .forEach(logger::info);
    }
}
