package gwt.xml.server.impl;

import gwt.xml.shared.impl.XPathImpl;
import gwt.xml.shared.impl.XmlParserImpl;

import java.util.stream.Stream;

public interface IProperty {
    default Stream<String> getKeys() {
        return Stream.of(XmlParserImpl.className, XPathImpl.className);
    }
}
