package gwt.xml.test.server;

import org.jspecify.annotations.NonNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.tomcat.servlet.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Objects;

@SpringBootApplication
@ServletComponentScan(basePackages = {"gwt.xml.test.server"})
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected @NonNull SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    /**
     * This inner class is relevant for RPC. If you're not using RPC, you can delete it. Otherwise, keeping it can be
     * beneficial during development.
     */
    @Component
    public static class EmbeddedServletContainerConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
        @Override
        public void customize(TomcatServletWebServerFactory factory) {
            /*
             A document root configuration is necessary. Without it, the RemoteServiceServlet cannot resolve the
             serializationPolicyFilePath on the embedded Spring Boot web server. As a result, the call to
             servlet.getServletContext().getResourceAsStream(serializationPolicyFilePath) returns null. This limitation
             means that only IsSerializable is viable for RPC serialization; java.io.Serializable will not work.
             */
            factory.setDocumentRoot(new File(Objects.requireNonNull(getClass().getResource("/")).getFile(),
                    "public"));
        }
    }
}
