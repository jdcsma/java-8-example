package jun.java8.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Application {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        regex_predicate_example();
        regex_matcher_example();
    }

    private static final String REGEX_1 = "^(177|133|139|186)[0-9]{8}$";
    private static final String REGEX_2 = "^(177|133|139|186)\\d{8}$";

    public static void regex_predicate_example() {
        logger.info("regex as predicate: -------------------");
        Stream<String> numbers = Stream.of("17701234567", "13312345678");
        Predicate<String> filter = Pattern.compile(REGEX_1).asPredicate();
        numbers.filter(filter).forEach(logger::info);
    }

    public static void regex_matcher_example() {
        logger.info("regex using matcher: -------------------");
        List<String> numbers = Arrays.asList("17701234567", "13312345678");
        Pattern pattern = Pattern.compile(REGEX_2);
        for (String number : numbers) {
            Matcher matcher = pattern.matcher(number);
            if (matcher.matches()) {
                logger.info(number);
            }
        }
    }
}
