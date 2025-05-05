package gwt.xml.shared.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Properties;
import java.util.function.Supplier;

public class SuperUtil {
    @SuppressWarnings("unchecked")
    public static <T> T findInstance(String propertyKey, Supplier<T> fallback) {
        try {
            Class<?> clazz = getClassLoader().loadClass(ConfigProperties.instance.getProperty(propertyKey));

            MethodHandles.Lookup lookup = MethodHandles.lookup();
            return (T) lookup.findConstructor(clazz, MethodType.methodType(void.class)).invoke();
        } catch (Throwable e) {
            return fallback.get();
        }
    }

    private static ClassLoader getClassLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        if (classLoader == null)
            return ClassLoader.getSystemClassLoader();

        return classLoader;
    }

    private SuperUtil() {
    }

    private static class ConfigProperties extends Properties {
        private static final String configProperties = "config.properties";

        private static final ConfigProperties instance = new ConfigProperties();

        private ConfigProperties() {
            try {
                Enumeration<URL> resources = getClassLoader().getResources(configProperties);
                while (resources.hasMoreElements()) {
                    try (InputStream inputStream = resources.nextElement().openStream()) {
                        if (inputStream != null) {
                            load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                        }
                    } catch (IOException e) {
                        // Ignore
                    }
                }
            } catch (IOException e) {
                // Ignore
            }
        }
    }
}
