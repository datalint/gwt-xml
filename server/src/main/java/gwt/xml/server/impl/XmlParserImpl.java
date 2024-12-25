package gwt.xml.server.impl;

import gwt.xml.server.dom.DocumentImpl;
import gwt.xml.server.parser.DocumentParser;
import org.w3c.dom.Document;

public class XmlParserImpl extends gwt.xml.shared.impl.XmlParserImpl {
    public Document createDocument() {
        return new DocumentImpl();
    }

    public Document parse(String contents) throws Exception {
        Document document = new DocumentImpl();

        document.appendChild(DocumentParser.parse(document, contents));

        return document;
    }
}
