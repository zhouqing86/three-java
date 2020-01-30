package guavabook;

import com.google.common.base.CharMatcher;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CharMatcherTest {
    @Test
    public void testReplaceFrom() throws Exception {
        String s = CharMatcher.breakingWhitespace().replaceFrom("abc\ndef", " ");
        assertEquals("abc def", s);
    }

    @Test
    public void removeMultipleTabsAndSpaces() throws Exception {
        String s = CharMatcher.whitespace().collapseFrom("hello   world!", ' ');
        assertEquals("hello world!", s);

        s = CharMatcher.whitespace().trimAndCollapseFrom("   hello   world! ", ' ');
        assertEquals("hello world!", s);
    }

    @Test
    public void testOrFunction() throws Exception {
        CharMatcher combineMatcher = CharMatcher.whitespace().or(CharMatcher.javaDigit());

        String s = combineMatcher.replaceFrom("abc 123def", "*");
        assertEquals("abc****def", s);
    }
}
