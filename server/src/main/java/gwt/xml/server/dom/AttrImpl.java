package gwt.xml.server.dom;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.TypeInfo;

public class AttrImpl extends NodeImpl implements Attr {
    private final String name;
    private String value;

    public AttrImpl(Node owner, String name, String value) {
        super(owner);

        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return getNodeName();
    }

    @Deprecated
    @Override
    public boolean getSpecified() {
        return true;
    }

    @Override
    public String getValue() {
        return getNodeValue();
    }

    @Override
    public void setValue(String value) {
        setNodeValue(value);
    }

    @Override
    public short getNodeType() {
        return ATTRIBUTE_NODE;
    }

    @Override
    public String getNodeName() {
        return name;
    }

    @Override
    public String getNodeValue() {
        return value;
    }

    @Override
    public void setNodeValue(String nodeValue) {
        value = nodeValue;

        if (owner instanceof Element)
            ((Element) owner).setAttribute(name, value);
    }

    @Override
    public String getTextContent() {
        return getNodeValue();
    }

    @Override
    public void setTextContent(String textContent) {
        setNodeValue(textContent);
    }

    @Override
    public Node cloneNode(boolean deep) {
        return new AttrImpl(getOwnerDocument(), name, value);
    }

    @Override
    public Node getParentNode() {
        // An attribute node does not have a parent node; instead, it has an owner element.
        return null;
    }

    @Override
    public Element getOwnerElement() {
        return owner instanceof Element ? (Element) owner : null;
    }

    @Override
    public TypeInfo getSchemaTypeInfo() {
        throw createUoException("getSchemaTypeInfo");
    }

    @Override
    public boolean isId() {
        throw createUoException("isId");
    }
}
