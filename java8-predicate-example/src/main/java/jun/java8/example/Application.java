package jun.java8.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Predicate;
import java.util.stream.Stream;


public class Application {

    private static final Logger logger = LogManager.getLogger();

    /**
     * <p>
     * In mathematics, a predicate is commonly understood to be a boolean-valued function
     * 'P: X? {true, false}', called the predicate on X.
     * </p>
     * <p>
     * Informally, a strong. It can be thought of as an operator or function that returns
     * a value that is either true or false.
     * </p>
     * <p>
     * In Java 8, Predicate is a functional interface and can therefore be used as the assignment
     * target for a lambda expression or method reference.
     * </p>
     */
    public static void main(String[] args) {
        predicate_lambda();
    }

    private static void predicate_lambda() {
        logger.info("predicate lambda: -------------------");
        Predicate<Integer> predicate = x -> x % 2 == 0;
        Stream.of(1, 2, 3, 4, 5, 6)
                // Stream<T> filter(Predicate<? super T> predicate);
                .filter(predicate)
                .forEach(logger::info);
    }
}
