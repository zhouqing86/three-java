package base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class StringTest {
    @Test
    public void testStringAddChar() throws Exception {
        String str = "Hello:";
        assertEquals("Hello:a", str+'a');
    }

    @Test
    public void testStringFormat() throws Exception {
        String str = String.format("float:%.2f,integer:%d", 1.1, 1);
        assertEquals("float:1.10,integer:1", str);
    }

    @Test
    public void testCompareWithStringBuffer() throws Exception {
        StringBuffer sb = new StringBuffer("hello");
        sb.append(",");
        sb.append("world");
        sb.append("!");
        assertTrue("hello,world!".contentEquals(sb));
    }

    @Test
    public void testCompareTwoString() throws Exception {
        String str1 = "abc";
        String str2 = "Abc";
        assertTrue(str1.compareTo(str2) > 0);
        assertTrue(str1.compareToIgnoreCase(str2) == 0);
    }

    @Test
    public void testStartWithEndsWith() throws Exception {
        assertTrue("abc,".endsWith(","));
        assertTrue("abc".startsWith("a"));
    }

    @Test
    public void testIndexOf() throws Exception {
        assertEquals(0, "abc".indexOf("a"));
    }

    @Test
    public void testSubString() throws Exception {
        assertEquals("bc", "abcd".substring(1,3));
    }

    @Test
    public void testMatcheRegex() throws Exception {
        assertTrue("abc123".matches("\\w+\\d+"));
    }

    @Test
    public void testStringSplit() throws Exception {
        String arrStr = "abc,def,ghh,,";
        String [] arr = arrStr.split(",");
        assertEquals(3, arr.length);
    }

    @Test
    public void testValueOfString() throws Exception {
        Integer i = 123;
        assertEquals("123", String.valueOf(i));
    }

    @Test
    public void testReverseString() throws Exception {
        String str = "abc";
        assertEquals("cba", new StringBuffer(str).reverse().toString());
        assertEquals("cba", new StringBuilder(str).reverse().toString());
    }
}
