package annotation.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class StringUtilTest {
    @Test
    public void testConvertCamelCaseStringToUnderlineString() throws Exception {
        assertEquals("ACCOUNTID", StringUtil.camelCaseToUnderline("accountid"));
        assertEquals("FIRST_HEAD_NAME", StringUtil.camelCaseToUnderline("firstHeadName"));
    }
}