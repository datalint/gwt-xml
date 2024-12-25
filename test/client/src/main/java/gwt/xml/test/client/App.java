package gwt.xml.test.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import gwt.xml.client.AssertionsC;
import gwt.xml.shared.XmlParser;

public class App implements EntryPoint {
    @Override
    public void onModuleLoad() {
        AssertionsC.setMessageConsumer((isPositive, message) -> RootPanel.get().add(new Label(isPositive + ": "
                + message)));

        AssertionsC.assertEquals("<a b=\"test\"/>", XmlParser.parse("<a b=\"test\"/>").toString());

        RootPanel.get().add(new Label("Hello, world (from TestServlet) " + XmlParser.parse("<a b='test'/>")));
    }
}
