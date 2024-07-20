package guavabook;

import com.google.common.base.Strings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringsTest {
    @Test
    public void testStringsPad() throws Exception {
        assertEquals("fooxxx", Strings.padEnd("foo", 6, 'x'));
    }

    @Test
    public void testStringNullOrEmpty() throws Exception {
        assertTrue(Strings.isNullOrEmpty(""));
        assertTrue(Strings.isNullOrEmpty(null));

        assertNull(Strings.emptyToNull(""));
        assertEquals("", Strings.nullToEmpty(null));
    }
}
