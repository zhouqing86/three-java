package annotation.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilTest {
    @Test
    public void testConvertCamelCaseStringToUnderlineString() throws Exception {
        assertEquals("ACCOUNTID", StringUtil.camelCaseToUnderline("accountid"));
        assertEquals("FIRST_HEAD_NAME", StringUtil.camelCaseToUnderline("firstHeadName"));
    }
}