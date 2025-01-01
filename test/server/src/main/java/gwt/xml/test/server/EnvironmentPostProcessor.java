package gwt.xml.test.server;

import gwt.xml.server.impl.IProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;

public class EnvironmentPostProcessor implements org.springframework.boot.env.EnvironmentPostProcessor, IProperty {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        getKeys().forEach(key -> setProperty(environment, key));
    }

    private void setProperty(ConfigurableEnvironment environment, String key) {
        String value = environment.getProperty(key);

        if (value != null)
            System.setProperty(key, value);
    }
}
