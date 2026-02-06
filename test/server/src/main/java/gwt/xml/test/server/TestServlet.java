package gwt.xml.test.server;

import gwt.xml.shared.XmlParser;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class TestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println("Hello, world (from Xml TestServlet) " + XmlParser.parse("<a b=\"xml test\"/>"));
    }
}
