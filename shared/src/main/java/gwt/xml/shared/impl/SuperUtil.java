package gwt.xml.shared.impl;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.function.Supplier;

public class SuperUtil {
    @SuppressWarnings("unchecked")
    public static <T> T findInstance(String propertyKey, Supplier<T> fallback) {
        try {
            Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass(System.getProperty(propertyKey));

            MethodHandles.Lookup lookup = MethodHandles.lookup();
            return (T) lookup.findConstructor(clazz, MethodType.methodType(void.class)).invoke();
        } catch (Throwable e) {
            return fallback.get();
        }
    }

    private SuperUtil() {
    }
}
