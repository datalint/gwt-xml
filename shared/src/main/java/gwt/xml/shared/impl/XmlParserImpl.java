package gwt.xml.shared.impl;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;

public class XmlParserImpl {
    public static final String className = XmlParserImpl.class.getName();

    public static XmlParserImpl getInstance() {
        return Lazy.instance;
    }

    protected XmlParserImpl() {
    }

    public Document createDocument() {
        return newDocumentBuilder().newDocument();
    }

    public Document parse(String contents) throws Exception {
        return newDocumentBuilder().parse(new ByteArrayInputStream(contents.getBytes()));
    }

    private DocumentBuilder newDocumentBuilder() {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            return null;
        }
    }

    public boolean supportsCDATASection() {
        return true;
    }

    private static class Lazy {
        private static final XmlParserImpl instance = SuperUtil.findInstance(className, XmlParserImpl::new);
    }
}
