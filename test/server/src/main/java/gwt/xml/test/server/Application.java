package gwt.xml.test.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Objects;

@SpringBootApplication
@ServletComponentScan(basePackages = {"gwt.xml.test"})
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    /**
     * You may delete this inner class if you do not use RPC, otherwise it is better to keep it for development.
     */
    @Component
    public static class EmbeddedServletContainerConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
        @Override
        public void customize(TomcatServletWebServerFactory factory) {
            /*
              You have to set a document root here, otherwise RemoteServiceServlet will fail to find the
              corresponding serializationPolicyFilePath on a temporary web server started by spring boot application:
              servlet.getServletContext().getResourceAsStream(serializationPolicyFilePath) returns null.
              This has impact that java.io.Serializable can be no more used in RPC, only IsSerializable works.
              */
            factory.setDocumentRoot(new File(Objects.requireNonNull(getClass().getResource("/")).getFile(),
                    "public"));
        }
    }
}
