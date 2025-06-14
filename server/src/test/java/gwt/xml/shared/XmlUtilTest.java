package gwt.xml.shared;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import static org.junit.jupiter.api.Assertions.*;
import static org.w3c.dom.Node.DOCUMENT_POSITION_CONTAINED_BY;
import static org.w3c.dom.Node.DOCUMENT_POSITION_CONTAINS;

class XmlUtilTest {
    @Test
    void testNormalizeSpace() {
        String s1 = "<domains revision=\"0\"><domain id=\"b1_1\" name=\"Tutorial &amp; Showcase Local\" template=\"tutorial\"><user id=\"administrator@datalint.com\" authorization=\"administrator\"/><user id=\"supervisor@datalint.com\" authorization=\"supervisor\"/><user id=\"developer@datalint.com\" authorization=\"developer\"/><user id=\"writer@datalint.com\" authorization=\"writer\"/><user id=\"reader@datalint.com\" authorization=\"reader\"/></domain><domain id=\"b1_2\" name=\"Tutorial &amp; Showcase Cloud\" template=\"tutorial\" authorization=\"owner\"><user id=\"administrator@datalint.com\" authorization=\"administrator\"/><user id=\"supervisor@datalint.com\" authorization=\"supervisor\"/><user id=\"developer@datalint.com\" authorization=\"developer\"/><user id=\"writer@datalint.com\" authorization=\"writer\"/><user id=\"reader@datalint.com\" authorization=\"reader\"/></domain><domain id=\"b1_3\" name=\"Inventory Management Local\" template=\"inventoryManagement\"><user id=\"administrator@datalint.com\" authorization=\"administrator\"/><user id=\"supervisor@datalint.com\" authorization=\"supervisor\"/><user id=\"developer@datalint.com\" authorization=\"developer\"/><user id=\"writer@datalint.com\" authorization=\"writer\"/><user id=\"reader@datalint.com\" authorization=\"reader\"/></domain><domain id=\"b1_4\" name=\"Inventory Management Cloud\" template=\"inventoryManagement\" authorization=\"owner\"><user id=\"administrator@datalint.com\" authorization=\"administrator\"/><user id=\"supervisor@datalint.com\" authorization=\"supervisor\"/><user id=\"developer@datalint.com\" authorization=\"developer\"/><user id=\"writer@datalint.com\" authorization=\"writer\"/><user id=\"reader@datalint.com\" authorization=\"reader\"/></domain></domains>";
        String s2 = "<domains revision=\"0\">\n" +
                "    <domain id=\"b1_1\" name=\"Tutorial &amp; Showcase Local\" template=\"tutorial\">\n" +
                "        <user id=\"administrator@datalint.com\" authorization=\"administrator\"/>\n" +
                "        <user id=\"supervisor@datalint.com\" authorization=\"supervisor\"/>\n" +
                "        <user id=\"developer@datalint.com\" authorization=\"developer\"/>\n" +
                "        <user id=\"writer@datalint.com\" authorization=\"writer\"/>\n" +
                "        <user id=\"reader@datalint.com\" authorization=\"reader\"/>\n" +
                "    </domain>\n" +
                "    <domain id=\"b1_2\" name=\"Tutorial &amp; Showcase Cloud\" template=\"tutorial\" authorization=\"owner\">\n" +
                "        <user id=\"administrator@datalint.com\" authorization=\"administrator\"/>\n" +
                "        <user id=\"supervisor@datalint.com\" authorization=\"supervisor\"/>\n" +
                "        <user id=\"developer@datalint.com\" authorization=\"developer\"/>\n" +
                "        <user id=\"writer@datalint.com\" authorization=\"writer\"/>\n" +
                "        <user id=\"reader@datalint.com\" authorization=\"reader\"/>\n" +
                "    </domain>\n" +
                "    <domain id=\"b1_3\" name=\"Inventory Management Local\" template=\"inventoryManagement\">\n" +
                "        <user id=\"administrator@datalint.com\" authorization=\"administrator\"/>\n" +
                "        <user id=\"supervisor@datalint.com\" authorization=\"supervisor\"/>\n" +
                "        <user id=\"developer@datalint.com\" authorization=\"developer\"/>\n" +
                "        <user id=\"writer@datalint.com\" authorization=\"writer\"/>\n" +
                "        <user id=\"reader@datalint.com\" authorization=\"reader\"/>\n" +
                "    </domain>\n" +
                "    <domain id=\"b1_4\" name=\"Inventory Management Cloud\" template=\"inventoryManagement\" authorization=\"owner\">\n" +
                "        <user id=\"administrator@datalint.com\" authorization=\"administrator\"/>\n" +
                "        <user id=\"supervisor@datalint.com\" authorization=\"supervisor\"/>\n" +
                "        <user id=\"developer@datalint.com\" authorization=\"developer\"/>\n" +
                "        <user id=\"writer@datalint.com\" authorization=\"writer\"/>\n" +
                "        <user id=\"reader@datalint.com\" authorization=\"reader\"/>\n" +
                "    </domain>\n" +
                "</domains>";

        Element domains1 = XmlParser.parse(s1).getDocumentElement();
        Element domains2 = XmlParser.parse(s2).getDocumentElement();

        assertFalse(domains1.isEqualNode(domains2));

        XmlUtil.normalizeSpace(domains2);
        assertTrue(domains1.isEqualNode(domains2));
    }

    @Test
    @Deprecated
    void testEqualsTrue() {
        String s1 = "<domains revision=\"0\"><domain id=\"b1_1\" name=\"Tutorial &amp; Showcase Local\" template=\"tutorial\"><user id=\"administrator@datalint.com\" authorization=\"administrator\"/><user id=\"supervisor@datalint.com\" authorization=\"supervisor\"/><user id=\"developer@datalint.com\" authorization=\"developer\"/><user id=\"writer@datalint.com\" authorization=\"writer\"/><user id=\"reader@datalint.com\" authorization=\"reader\"/></domain><domain id=\"b1_2\" name=\"Tutorial &amp; Showcase Cloud\" template=\"tutorial\" authorization=\"owner\"><user id=\"administrator@datalint.com\" authorization=\"administrator\"/><user id=\"supervisor@datalint.com\" authorization=\"supervisor\"/><user id=\"developer@datalint.com\" authorization=\"developer\"/><user id=\"writer@datalint.com\" authorization=\"writer\"/><user id=\"reader@datalint.com\" authorization=\"reader\"/></domain><domain id=\"b1_3\" name=\"Inventory Management Local\" template=\"inventoryManagement\"><user id=\"administrator@datalint.com\" authorization=\"administrator\"/><user id=\"supervisor@datalint.com\" authorization=\"supervisor\"/><user id=\"developer@datalint.com\" authorization=\"developer\"/><user id=\"writer@datalint.com\" authorization=\"writer\"/><user id=\"reader@datalint.com\" authorization=\"reader\"/></domain><domain id=\"b1_4\" name=\"Inventory Management Cloud\" template=\"inventoryManagement\" authorization=\"owner\"><user id=\"administrator@datalint.com\" authorization=\"administrator\"/><user id=\"supervisor@datalint.com\" authorization=\"supervisor\"/><user id=\"developer@datalint.com\" authorization=\"developer\"/><user id=\"writer@datalint.com\" authorization=\"writer\"/><user id=\"reader@datalint.com\" authorization=\"reader\"/></domain></domains>";
        String s2 = "<domains revision=\"0\"><domain id=\"b1_1\" name=\"Tutorial &amp; Showcase Local\" template=\"tutorial\"><user id=\"administrator@datalint.com\" authorization=\"administrator\"/><user id=\"supervisor@datalint.com\" authorization=\"supervisor\"/><user id=\"developer@datalint.com\" authorization=\"developer\"/><user id=\"writer@datalint.com\" authorization=\"writer\"/><user id=\"reader@datalint.com\" authorization=\"reader\"/></domain><domain id=\"b1_2\" name=\"Tutorial &amp; Showcase Cloud\" template=\"tutorial\" authorization=\"owner\"><user id=\"administrator@datalint.com\" authorization=\"administrator\"/><user id=\"supervisor@datalint.com\" authorization=\"supervisor\"/><user id=\"developer@datalint.com\" authorization=\"developer\"/><user id=\"writer@datalint.com\" authorization=\"writer\"/><user id=\"reader@datalint.com\" authorization=\"reader\"/></domain><domain id=\"b1_3\" name=\"Inventory Management Local\" template=\"inventoryManagement\"><user id=\"administrator@datalint.com\" authorization=\"administrator\"/><user id=\"supervisor@datalint.com\" authorization=\"supervisor\"/><user id=\"developer@datalint.com\" authorization=\"developer\"/><user id=\"writer@datalint.com\" authorization=\"writer\"/><user id=\"reader@datalint.com\" authorization=\"reader\"/></domain><domain id=\"b1_4\" name=\"Inventory Management Cloud\" template=\"inventoryManagement\" authorization=\"owner\"><user id=\"administrator@datalint.com\" authorization=\"administrator\"/><user id=\"supervisor@datalint.com\" authorization=\"supervisor\"/><user id=\"developer@datalint.com\" authorization=\"developer\"/><user id=\"writer@datalint.com\" authorization=\"writer\"/><user id=\"reader@datalint.com\" authorization=\"reader\"/></domain></domains>";

        assertTrue(XmlUtil.equals(XmlParser.parse(s1).getDocumentElement(), XmlParser.parse(s2).getDocumentElement()));
    }

    @Test
    @Deprecated
    void testEqualsFalse() {
        String s1 = "<record name=\"BURBERRY Dovey T-shirt with logo embroidery\"><table idRef=\"g1\"><row semiId=\"g1r_1\"><column idRef=\"g1c_3\">Burberry</column><column idRef=\"g1c_4\">womensFashion</column><column idRef=\"g1c_1\">COMPOSITION\n" +
                "Dry cleaning: Dry cleaning not possible\n" +
                "Drying instructions: Dry horizontally\n" +
                "Material: 100% cotton\n" +
                "Pattern: Uni\n" +
                "Stretch: Mid-weight material with stretch\n" +
                "Ironing instructions: Iron warm (max. 110º)\n" +
                "Washing instructions: Maximum at 30º\n" +
                "\n" +
                "SPECIFICATIONS\n" +
                "Neckline: Round\n" +
                "Sleeve length: Short sleeve\n" +
                "\n" +
                "FIT\n" +
                "Fit: Loose straight silhouette\n" +
                "Length: 63 cm in size S.\n" +
                "Model and Size: Our model is 180 cm tall and wears size S, this size is normal.\n" +
                "\n" +
                "PRODUCT INFORMATION\n" +
                "Fashion house BURBERRY was founded in 1856 by Thomas Burberry in London. BURBERRY clothing and accessories are often recognizable by the camel-colored pattern with black, red and white stripes; the so-called Haymarket diamond. The brand is known worldwide for its British design.</column><column idRef=\"g1c_10\">XS,S,M,L</column><column idRef=\"g1c_9\">White,Black</column><column idRef=\"g1c_2\">1200.01</column><column idRef=\"g1c_5\">2020-04-08</column><column idRef=\"g1c_8\">2895,4.webp,2899,8.webp,2907,2.webp,2883,1.webp,2903,5.webp,2911,6.webp</column><column idRef=\"g1c_6\">2</column></row></table></record>";
        String s2 = "<record name=\"BURBERRY Dovey T-shirt with logo embroidery\"><table idRef=\"g1\"><row semiId=\"g1r_1\"><column idRef=\"g1c_3\">Burberry</column><column idRef=\"g1c_4\">womensFashion</column><column idRef=\"g1c_1\">COMPOSITION\n" +
                "Dry cleaning: Dry cleaning not possible\n" +
                "Drying instructions: Dry horizontally\n" +
                "Material: 100% cotton\n" +
                "Pattern: Uni\n" +
                "Stretch: Mid-weight material with stretch\n" +
                "Ironing instructions: Iron warm (max. 110º)\n" +
                "Washing instructions: Maximum at 30º\n" +
                "\n" +
                "SPECIFICATIONS\n" +
                "Neckline: Round\n" +
                "Sleeve length: Short sleeve\n" +
                "\n" +
                "FIT\n" +
                "Fit: Loose straight silhouette\n" +
                "Length: 63 cm in size S.\n" +
                "Model and Size: Our model is 180 cm tall and wears size S, this size is normal.\n" +
                "\n" +
                "PRODUCT INFORMATION\n" +
                "Fashion house BURBERRY was founded in 1856 by Thomas Burberry in London. BURBERRY clothing and accessories are often recognizable by the camel-colored pattern with black, red and white stripes; the so-called Haymarket diamond. The brand is known worldwide for its British design.</column><column idRef=\"g1c_10\">XS,S,M,L</column><column idRef=\"g1c_9\">White,Black</column><column idRef=\"g1c_2\">120</column><column idRef=\"g1c_5\">2020-04-08</column><column idRef=\"g1c_8\">2895,4.webp,2899,8.webp,2907,2.webp,2883,1.webp,2903,5.webp,2911,6.webp</column><column idRef=\"g1c_6\">2</column></row></table></record>";

        assertFalse(XmlUtil.equals(XmlParser.parse(s1).getDocumentElement(), XmlParser.parse(s2).getDocumentElement()));
    }

    private Document createDocument() {
        return XmlParser.parse("""
                <a>
                    <b id='b1'><c/></b>
                    <b id='b2'><c/></b>
                </a>
                """);
    }

    @Test
    void testGetClosestCommonAncestor() {
        Document document = createDocument();

        Element b1 = document.getElementById("b1");
        Element b2 = document.getElementById("b2");

        assertEquals(document.getDocumentElement(), XmlUtil.getClosestCommonAncestor(b1, b2));
        assertEquals(document.getDocumentElement(), XmlUtil.getClosestCommonAncestor(b1, b2.getFirstChild()));
        assertEquals(b1, XmlUtil.getClosestCommonAncestor(b1, b1.getFirstChild()));
        assertEquals(b1, XmlUtil.getClosestCommonAncestor(b1.getFirstChild(), b1));
    }

    @Test
    void testDetermineRelation() {
        Document document = createDocument();

        Element b1 = document.getElementById("b1");
        Element b2 = document.getElementById("b2");

        assertEquals(0, XmlUtil.determineRelation(b1, b2));
        assertEquals(0, XmlUtil.determineRelation(b1, b2.getFirstChild()));
        assertEquals(0, XmlUtil.determineRelation(b1.getFirstChild(), b2));
        assertEquals(0, XmlUtil.determineRelation(b1.getFirstChild(), b2.getFirstChild()));

        assertEquals(DOCUMENT_POSITION_CONTAINED_BY, XmlUtil.determineRelation(b1, b1));
        assertEquals(DOCUMENT_POSITION_CONTAINED_BY, XmlUtil.determineRelation(b1, b1.getFirstChild()));
        assertEquals(DOCUMENT_POSITION_CONTAINED_BY, XmlUtil.determineRelation(b1.getParentNode(), b1.getFirstChild()));
        assertEquals(DOCUMENT_POSITION_CONTAINS, XmlUtil.determineRelation(b1.getFirstChild(), b1));
        assertEquals(DOCUMENT_POSITION_CONTAINS, XmlUtil.determineRelation(b1.getFirstChild(), b1.getParentNode()));
    }
}
