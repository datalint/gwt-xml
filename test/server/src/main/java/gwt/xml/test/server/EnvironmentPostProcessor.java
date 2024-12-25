package gwt.xml.test.server;

import gwt.xml.shared.impl.XPathImpl;
import gwt.xml.shared.impl.XmlParserImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;

public class EnvironmentPostProcessor implements org.springframework.boot.env.EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        setProperty(environment, XmlParserImpl.className);
        setProperty(environment, XPathImpl.className);
    }

    private void setProperty(ConfigurableEnvironment environment, String key) {
        String value = environment.getProperty(key);

        if (value != null)
            System.setProperty(key, value);
    }
}
