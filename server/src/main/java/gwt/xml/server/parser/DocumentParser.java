package gwt.xml.server.parser;

import com.google.common.io.CharStreams;
import gwt.xml.server.common.BasePooledObjectFactory;
import gwt.xml.server.dom.DocumentImpl;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class DocumentParser {
    private static String asXml(InputSource inputSource) throws IOException {
        InputStream inputStream = inputSource.getByteStream();

        if (inputStream != null)
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

        return CharStreams.toString(inputSource.getCharacterStream());
    }

    public static Document parse(String xml) throws Exception {
        return parse(xml, 0);
    }

    public static Document parse(String xml, int options) throws Exception {
        Document document = new DocumentImpl();

        document.appendChild(parse(document, xml, options));

        return document;
    }

    public static Document parse(InputSource inputSource) throws Exception {
        return parse(asXml(inputSource));
    }

    public static Document parseSilent(String xml) {
        return parseSilent(xml, 0);
    }

    public static Document parseSilent(String xml, int options) {
        try {
            return parse(xml, options);
        } catch (Exception e) {
            return null;
        }
    }

    public static Document parseSilent(InputSource inputSource) {
        try {
            return parse(inputSource);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T extends DefaultHandler> T parse(String xml, T handler) throws Exception {
        SAXParser saxParser = ParserFactory.instance.borrowObject();

        try {
            saxParser.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)), handler);

            return handler;
        } finally {
            ParserFactory.instance.returnObject(saxParser);
        }
    }

    public static <T extends DefaultHandler> T parseSilent(String xml, T handler) {
        try {
            return parse(xml, handler);
        } catch (Exception e) {
            return null;
        }
    }

    public static Element parse(Document owner, String xml) throws Exception {
        return parse(owner, xml, 0);
    }

    public static Element parse(Document owner, String xml, int options) throws Exception {
        return parse(xml, new DocumentSaxHandler(owner, options)).getRootElement();
    }

    public static Element parseSilent(Document owner, String xml) {
        try {
            return parse(owner, xml);
        } catch (Exception e) {
            return null;
        }
    }

    private static class ParserFactory extends BasePooledObjectFactory<SAXParser> {
        private static ObjectPool<SAXParser> instance = new GenericObjectPool<>(new ParserFactory());

        @Override
        public SAXParser create() throws Exception {
            return SAXParserFactory.newInstance().newSAXParser();
        }

        @Override
        public void passivateObject(PooledObject<SAXParser> wrapper) {
            wrapper.getObject().reset();
        }
    }
}
