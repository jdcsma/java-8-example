package jun.java8.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;


public class Application {

    private static final Logger logger = LogManager.getLogger();

    /**
     * <p>
     *     In Java null is actually a type, a special one. It has no name so we cannot
     *     declare variables of its type or cast any variables to it; in fact there is
     *     only a single value that can be associated with it (i.e. the literal null).
     *     Remember that unlike any other types in Java, a null reference can be safely
     *     assigned to any other reference types without any error.
     * </p>
     *
     * <p>
     *     Optional is a way of replacing a nullable T reference with a non-null value.
     *     An Optional may either contain a non-null T reference (in which case we say
     *     the reference is “present”), or it may contain nothing (in which case we say
     *     the reference is “absent”).
     * </p>
     */
    public static void main(String[] args) {
        optional_create();
        optional_present();
        optional_absent();
        optional_filter();
    }

    private static void optional_create() {
        logger.info("optional creation: -------------------");
        Optional<Integer> empty = Optional.empty();
        logger.info("Optional.empty():{}", empty);
        Optional<Integer> notEmpty = Optional.of(5);
        logger.info("Optional.of:{}", notEmpty);

        Supplier<Integer> supplier = () -> {
            int randValue = new Random().nextInt();
            return randValue % 2 == 0 ? null : randValue;
        };
        // Return Optional.empty() if value is reference to null object.
        Optional<Integer> nullable = Optional.ofNullable(supplier.get());
        logger.info("Optional.ofNullable:{}", nullable);
    }

    private static void optional_present() {
        logger.info("optional present: -------------------");
        Optional<Integer> value = Optional.of(1);
        value.ifPresent(logger::info);
        if (value.isPresent()) {
            logger.info(value.get());
        }
    }

    private static void optional_absent() {
        logger.info("optional absent: -------------------");
        Optional<Integer> value = Optional.ofNullable(null);
        logger.info(value.orElse(10));
        logger.info(value.orElseGet(() -> 10));

        try {
            value.orElseThrow(IllegalArgumentException::new);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void optional_filter() {
        Optional<String> companyOptional = Optional.of("Yes");
        companyOptional.filter(v -> "Yes".equals(v))
                .ifPresent(logger::info);
    }
}
