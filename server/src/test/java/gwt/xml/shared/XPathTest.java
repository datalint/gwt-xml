package gwt.xml.shared;

import org.junit.jupiter.api.Test;
import org.w3c.dom.*;

import java.util.Arrays;
import java.util.List;

import static gwt.xml.shared.XPath.*;
import static gwt.xml.shared.XmlUtil.getFirstElementChild;
import static org.junit.jupiter.api.Assertions.*;

class XPathTest {
    @Test
    void testEvaluate() {
        String xml = "<web><domain id='1'/><domain id='2'>domain <child/> 2</domain></web>";
        Element element = XmlParser.parse(xml).getDocumentElement();

        assertEquals("<domain id=\"2\">domain <child/> 2</domain>", evaluateNode(element, "domain[@id='2']").toString());
        assertEquals("domain ", evaluateNode(element, "domain[@id='2']/text()").toString());
        assertEquals("[<domain id=\"2\">domain <child/> 2</domain>]", evaluateNodes(element, "domain[@id='2']").toString());
        assertEquals("[domain ,  2]", evaluateNodes(element, "domain[@id='2']/text()").toString());

        assertEquals("", evaluateString(element, "domain"));
        assertEquals("domain ", evaluateString(element, "domain/text()"));
        assertEquals("", evaluateString(element, "domain[@id='1']"));
        assertEquals("", evaluateString(element, "domain[@id='1']/text()"));
        assertEquals("domain  2", evaluateString(element, "domain[@id='2']"));
        assertEquals("domain ", evaluateString(element, "domain[@id='2']/text()"));

        assertEquals("domain ", XPath.<Text>evaluateNode(element, "domain/text()").toString());
        assertNull(XPath.<Text>evaluateNode(element, "domain[@id='1']/text()"));
        assertEquals("domain ", XPath.<Text>evaluateNode(element, "domain[@id='2']/text()").toString());
        assertEquals("domain ", evaluateText(XPath.<Element>evaluateNode(element, "domain[@id='2']")));

        assertTrue(evaluateNodes(element, "domain/@id").get(0) instanceof Attr);
        assertEquals(Arrays.asList("1", "2"), evaluateListAttrValues(element, "domain/@id"));

        assertEquals(getFirstElementChild(element), evaluateElement(element));
        assertEquals(getFirstElementChild(element), element.getFirstChild());

        xml = "<web>\n<domain id='1'/><domain id='2'/></web>";
        element = XmlParser.parse(xml).getDocumentElement();
        assertEquals(getFirstElementChild(element), evaluateElement(element));
        assertEquals(evaluateNode(element, "text()"), element.getFirstChild());
    }

    @Test
    void testImportNode() {
        Document document = XmlParser.createDocument();

        Node root = document.createElement("root");

        Node parent = document.createElement("parent");
        root.appendChild(parent);

        Node child = document.createElement("child");
        parent.appendChild(child);

        Node childTwo = document.createElement("child");
        parent.appendChild(childTwo);

        Node parentTwo = document.createElement("parent");
        root.appendChild(parentTwo);

        Document newDocument = XmlParser.createDocument();
        root = newDocument.importNode(root, true);

        List<Element> descendants = evaluateNodes((Element) root, ".//*");
        parent = root.getFirstChild();
        assertEquals(List.of(parent, parent.getFirstChild(), parent.getLastChild(), root.getLastChild()), descendants);
    }
}
