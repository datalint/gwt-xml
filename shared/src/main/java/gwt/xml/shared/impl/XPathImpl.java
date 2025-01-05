package gwt.xml.shared.impl;

import gwt.xml.shared.ICommon;
import gwt.xml.shared.common.CollectionMode;
import gwt.xml.shared.common.TriConsumer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class XPathImpl
        implements ICommon {
    public static final String className = XPathImpl.class.getName();

    public static XPathImpl getInstance() {
        return Lazy.instance;
    }

    protected XPathImpl() {
    }

    public String[] evaluateAttrValues(Element element, String xPath) {
        NodeList nodeList = evaluateNodeList(element, xPath);

        int length = nodeList.getLength();

        String[] attrValues = new String[length];

        for (int i = 0; i < length; i++) {
            attrValues[i] = nodeList.item(i).getNodeValue();
        }

        return attrValues;
    }

    public Set<String> evaluateAttrValues(Element element, String xPath, CollectionMode mode) {
        return evaluateNodeValues(element, xPath, mode);
    }

    public Set<String> evaluateTextValues(Element element, String xPath, CollectionMode mode) {
        return evaluateNodeValues(element, xPath, mode);
    }

    private Set<String> evaluateNodeValues(Element element, String xPath, CollectionMode mode) {
        NodeList nodeList = evaluateNodeList(element, xPath);

        int length = nodeList.getLength();

        Set<String> nodeValues = mode.createSet(length);

        appendNodeValues(nodeValues, nodeList, length);

        return nodeValues;
    }

    private void appendNodeValues(Collection<String> nodeValues, NodeList nodeList, int length) {
        for (int i = 0; i < length; i++) {
            nodeValues.add(nodeList.item(i).getNodeValue());
        }
    }

    public Set<Element> evaluateElements(Element element, String xPath, CollectionMode mode) {
        NodeList nodeList = evaluateNodeList(element, xPath);

        int length = nodeList.getLength();
        Set<Element> elements = mode.createSet(length);
        for (int i = 0; i < length; i++) {
            elements.add((Element) nodeList.item(i));
        }

        return elements;
    }

    public <T> Map<String, T> evaluateElementsMap(Element element, String xPath, String attributeName,
                                                  CollectionMode mode, TriConsumer<Map<String, T>, String, Element> consumer) {
        NodeList nodeList = evaluateNodeList(element, xPath);

        int length = nodeList.getLength();
        Map<String, T> elementsMap = mode.createMap(length);

        for (int i = 0; i < length; i++) {
            Element item = (Element) nodeList.item(i);

            consumer.accept(elementsMap, item.getAttribute(attributeName), item);
        }

        return elementsMap;
    }

    public List<Node> evaluateNodes(Element element, String xPath) {
        return asList(evaluateNodeList(element, xPath));
    }

    public NodeList evaluateNodeList(Element element, String xPath) {
        return (NodeList) evaluate(element, xPath, XPathConstants.NODESET);
    }

    public Node evaluateNode(Element element, String xPath) {
        return (Node) evaluate(element, xPath, XPathConstants.NODE);
    }

    public String evaluateString(Element element, String xPath) {
        return (String) evaluate(element, xPath, XPathConstants.STRING);
    }

    public double evaluateNumber(Element element, String xPath) {
        return (Double) evaluate(element, xPath, XPathConstants.NUMBER);
    }

    public int evaluatePosition(Element element, String xPath, Element reference) {
        NodeList nodeList = evaluateNodeList(element, xPath);

        int length = nodeList.getLength();
        for (int i = 0; i < length; i++) {
            if (nodeList.item(i) == reference)
                return i + 1;
        }

        return 0;
    }

    protected Object evaluate(Element context, String xPath, QName qName) {
        try {
            return XPathFactory.newInstance().newXPath().compile(xPath).evaluate(context, qName);
        } catch (Exception e) {
            return null;
        }
    }

    private static class Lazy {
        private static final XPathImpl instance = SuperUtil.findInstance(className, XPathImpl::new);
    }
}
