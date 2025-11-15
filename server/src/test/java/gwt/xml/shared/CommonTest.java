package gwt.xml.shared;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommonTest implements ICommon {
    @Test
    void testAfterAndBefore() {
        String source = "com.datalint.core";

        assertEquals("datalint.core", after(source, '.'));
        assertEquals(source, after(source, ' '));

        assertEquals("core", afterLast(source, '.'));
        assertEquals(source, afterLast(source, ' '));

        assertEquals("com", before(source, '.'));
        assertEquals(source, before(source, ' '));

        assertEquals("com.datalint", beforeLast(source, '.'));
        assertEquals(source, beforeLast(source, ' '));
    }

    @Test
    void testCommonS() {
        String source = "com.datalint.core";
        char c = '.';

        assertEquals(before(source, c), Common.instance.before(source, c));
    }
}
