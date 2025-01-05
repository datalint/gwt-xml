package gwt.xml.shared.impl;

import java.util.function.Supplier;

public class SuperUtil {
    public static <T> T findInstance(String propertyKey, Supplier<T> fallback) {
        return fallback.get();
    }

    private SuperUtil() {
    }
}
