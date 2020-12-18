package jun.java8.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Application {

    private static final Logger logger = LogManager.getLogger();

    /**
     * As name implies, default methods in java 8 are simply default.
     * If you do not override them, they are the methods which will be
     * invoked by caller classes. They are defined in interfaces.
     *
     * <blockquote>
     *     Default methods enable you to add new functionality to the interfaces of your libraries
     *     and ensure binary compatibility with code written for older versions of those interfaces.
     * </blockquote>
     */
    public static void main(String[] args) {
        default_method();
        default_method_conflicts();
    }

    public interface Action {

        default void doAction() {
            logger.info("call default method.");
        }
    }

    private static void default_method() {
        logger.info("default_method: -------------------");

        Action action1 = new Action() {
        };
        action1.doAction();

        Action action2 = new Action() {
            @Override
            public void doAction() {
                logger.info("call overridden default method");
            }
        };
        action2.doAction();
    }

    public interface InterfaceA {
        default void call() {
            logger.info("call InterfaceA method.");
        }
    }

    public interface InterfaceB extends InterfaceA {
        default void call() {
            logger.info("call InterfaceB method.");
        }
    }

    public interface InterfaceC extends InterfaceB {
        default void call() {
            logger.info("call InterfaceC method.");
        }
    }

    public interface InterfaceOne {
        default void call() {
            logger.info("call InterfaceOne method.");
        }
    }

    public interface InterfaceTwo {
        default void call() {
            logger.info("call InterfaceTwo method.");
        }
    }

    public static class MyClass implements InterfaceB {

    }

    public static class Conflicts implements InterfaceOne, InterfaceTwo {

        private String chosenSupper;

        public Conflicts setChosenSupper(String chosenSupper) {
            this.chosenSupper = chosenSupper;
            return this;
        }

        @Override
        public void call() {
            if (this.chosenSupper.equals("InterfaceOne")) {
                InterfaceOne.super.call();
            } else {
                InterfaceTwo.super.call();
            }
        }
    }

    /**
     * <b>Rules for this conflict resolution are as follows:</b>
     *
     * <ol>
     *     <li>
     *         Most preferred are the overridden methods in classes.
     *         They will be matched and called if found before matching anything.
     *     </li>
     *     <li>
     *         The method with the same signature in the “most specific default-providing
     *         interface” is selected. This means if class MyClass implements two interfaces i.e.
     *         InterfaceA and InterfaceB such that InterfaceB extends InterfaceA. Then InterfaceB
     *         is here most specific interface and default method will be chosen from here if
     *         method signature is matched.
     *     </li>
     *     <li>
     *         If InterfaceA and InterfaceB are independent interfaces then a serious conflict condition
     *         happen, and compiler will complain then it is unable to decide. The you have to help
     *         compiler by providing extra info that from which interface the default method should be
     *         called. e.g.
     *     </li>
     * </ol>
     */
    private static void default_method_conflicts() {
        logger.info("default_method_conflicts: -------------------");

        // case 1
        InterfaceC ic = new InterfaceC() {
        };
        ic.call();

        // case 2
        MyClass mc = new MyClass();
        mc.call();

        // case 3
        Conflicts c = new Conflicts();
        c.setChosenSupper("InterfaceOne").call();
        c.setChosenSupper("InterfaceTwo").call();
    }
}
