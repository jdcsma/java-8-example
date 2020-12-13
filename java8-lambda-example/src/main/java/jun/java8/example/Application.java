package jun.java8.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Application {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        lambda_expression();
        function_interface();
    }

    /**
     * <p>
     * A Lambda expression (or function) is an <b>anonymous function</b>, i.e.,
     * a function with no name and any identifier.
     * </p>
     * <blockquote>
     * Lambda expressions are nameless functions given as constant values,
     * and written exactly in the place where it’s needed, typically as
     * a parameter to some other function.
     * </blockquote>
     * <p>
     * The most important feature of Lambda Expressions is that <b>they execute
     * in the context of their appearance</b>. So, a similar lambda expression can
     * be executed differently in some other context (i.e. logic will be the
     * same but results will be different based on different parameters passed
     * to function).
     * </p>
     */
    private static void lambda_expression() {
        logger.info("lambda_expression: -------------------");
        Map<Integer, String> phoneBook = new HashMap<>();
        phoneBook.put(1, "1");
        phoneBook.put(2, "2");
        phoneBook.forEach((k, v) -> logger.info("k:{} v:{}", k, v));
    }

    /**
     * <p>
     * Functional interfaces are new additions in java 8 which <b>permit
     * exactly one abstract method inside them</b>. These interfaces are also
     * called <b>Single Abstract Method interfaces (SAM Interfaces)</b>.
     * </p>
     * <p>
     * In Java 8, functional interfaces <b>can be represented using lambda expressions,
     * method reference and constructor references as well</b>.
     * </p>
     * <p>
     * Java 8 introduces an annotation i.e. <b>@FunctionalInterface</b> too, which can be
     * used for compiler level errors when the interface you have annotated violates
     * the contracts of exactly one abstract method.
     * </p>
     * <ul>
     *     <li>
     *         Only one abstract method is allowed in any functional interface.
     *         Second abstract method is not permitted in a functional interface.
     *         If we remove @FunctionInterface annotation then we are allowed to add
     *         another abstract method, but it will make the interface non-functional
     *         interface.
     *      </li>
     *      <li>
     *          A functional interface is valid even if the @FunctionalInterface annotation
     *          would be omitted. It is only for informing the compiler to enforce single abstract
     *          method inside interface.
     *      </li>
     *      <li>
     *          Conceptually, a functional interface has exactly one abstract method.
     *          Since default methods have an implementation, they are not abstract.
     *          Since default methods are not abstract you’re free to add default methods
     *          to your functional interface as many as you like.
     *          <pre>
     *          &#64FunctionalInterface
     *          public interface MyFirstFunctionalInterface {
     *
     *              public void firstWork();
     *
     *              default void doSomeMoreWork1() {
     *                  // Method body
     *              }
     *
     *              default void doSomeMoreWork2() {
     *                  // Method body
     *              }
     *          }
     *          </pre>
     *      </li>
     *      <li>
     *          If an interface declares an abstract method overriding one of the public methods
     *          of java.lang.Object, that also does not count toward the interface’s abstract method
     *          count since any implementation of the interface will have an implementation from
     *          java.lang.Object or elsewhere. e.g. Comparator is a functional interface even though
     *          it declared two abstract methods. Why? Because one of these abstract methods “equals()”
     *          which has signature equal to public method in Object class.
     *          <p>
     *          e.g. Below interface is a valid functional interface.
     *          <pre>
     *          &#64FunctionalInterface
     *          public interface MyFirstFunctionalInterface {
     *              public void firstWork();
     *
     *              &#64Override
     *              public String toString(); // Overridden from Object class
     *
     *              &#64Override
     *              public boolean equals(Object obj); // Overridden from Object class
     *          }
     *          </pre>
     *          </p>
     *      </li>
     * </ul>
     */
    private static void function_interface() {
        logger.info("function_interface: -------------------");
        foo(() -> "Returning value from function interface");
    }

    @FunctionalInterface
    private interface CustomFunctionInterface<T> {
        T get();
    }

    private static <T> void foo(CustomFunctionInterface<T> supplier) {
        logger.info(supplier.get());
    }

}
