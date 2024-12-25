package gwt.xml.server.impl;

import gwt.xml.server.common.BaseKeyedPooledObjectFactory;
import net.sf.saxon.xpath.XPathFactoryImpl;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

public class XPathImpl extends gwt.xml.shared.impl.XPathImpl {
    private static final GenericKeyedObjectPool<String, XPathExpression> XPATH_EXPRESSION_POOL =
            new GenericKeyedObjectPool<>(new XPathExpressionFactory());

    @Override
    protected Object evaluate(Element context, String xPath, QName qName) {
        try {
            XPathExpression xPathExpression = XPATH_EXPRESSION_POOL.borrowObject(xPath);

            try {
                return xPathExpression.evaluate(context, qName);
            } finally {
                XPATH_EXPRESSION_POOL.returnObject(xPath, xPathExpression);
            }
        } catch (Exception e) {
            return null;
        }
    }

    private static class XPathExpressionFactory extends BaseKeyedPooledObjectFactory<String, XPathExpression> {
        private static final XPathFactory xPathFactory = new XPathFactoryImpl();

        @Override
        public XPathExpression create(String key) throws Exception {
            return xPathFactory.newXPath().compile(key);
        }
    }
}
