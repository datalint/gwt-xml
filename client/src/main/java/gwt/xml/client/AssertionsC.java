package gwt.xml.client;

import gwt.xml.shared.ICommon;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class AssertionsC implements ICommon {
    private static BiConsumer<Boolean, String> messageConsumer;

    public static void setMessageConsumer(BiConsumer<Boolean, String> messageConsumer) {
        AssertionsC.messageConsumer = messageConsumer;
    }

    public static <A> boolean assertEquals(A actual, Predicate<A> predicate, String message) {
        boolean isPositive = predicate.test(actual);

        messageConsumer.accept(isPositive, isPositive || message == null ? createMessage(actual, isPositive) : message);

        return isPositive;
    }

    private static <E, A> boolean addMessage(E expected, A actual, boolean isPositive, boolean mask, String message) {
        messageConsumer.accept(isPositive, isPositive || message == null ? createMessage(expected, actual,
                isPositive ^ mask) : message);

        return isPositive;
    }

    public static <A> boolean assertEquals(A actual, Predicate<A> predicate) {
        return assertEquals(actual, predicate, (String) null);
    }

    public static <E, A> boolean assertEquals(E expected, A actual, BiPredicate<E, A> predicate, String message) {
        return addMessage(expected, actual, predicate.test(expected, actual), false, message);
    }

    public static <E, A> boolean assertEquals(E expected, A actual, BiPredicate<E, A> predicate) {
        return assertEquals(expected, actual, predicate, null);
    }

    public static boolean assertEquals(Object expected, Object actual) {
        return assertEquals(expected, actual, (String) null);
    }

    public static boolean assertEquals(Object expected, Object actual, String message) {
        return assertEquals(expected, actual, Objects::equals, message);
    }

    public static <E, A> boolean assertNotEquals(E expected, A actual, BiPredicate<E, A> predicate, String message) {
        return addMessage(expected, actual, !predicate.test(expected, actual), true, message);
    }

    public static <E, A> boolean assertNotEquals(E expected, A actual, BiPredicate<E, A> predicate) {
        return assertNotEquals(expected, actual, predicate, null);
    }

    public static boolean assertNotEquals(Object expected, Object actual) {
        return assertNotEquals(expected, actual, (String) null);
    }

    public static boolean assertNotEquals(Object expected, Object actual, String message) {
        return assertNotEquals(expected, actual, Objects::equals, message);
    }

    public static boolean assertNull(Object actual) {
        return assertEquals(null, actual);
    }

    public static boolean assertFalse(Object actual) {
        return assertEquals(Boolean.FALSE, actual);
    }

    public static boolean assertTrue(Object actual) {
        return assertEquals(Boolean.TRUE, actual);
    }

    private static String createMessage(Object actual, boolean equals) {
        StringBuilder builder = new StringBuilder();

        builder.append(_APOSTROPHE).append(actual).append(_APOSTROPHE);

        if (equals)
            builder.append(" EXPECTED");
        else
            builder.append(" NOT EXPECTED");

        return builder.toString();
    }

    private static String createMessage(Object expected, Object actual, boolean equals) {
        StringBuilder builder = new StringBuilder();

        builder.append(_APOSTROPHE).append(expected).append(_APOSTROPHE);

        if (equals)
            builder.append(" EQUALS ");
        else
            builder.append(" NOT EQUALS ");

        return builder.append(_APOSTROPHE).append(actual).append(_APOSTROPHE).toString();
    }

    private AssertionsC() {
    }
}
