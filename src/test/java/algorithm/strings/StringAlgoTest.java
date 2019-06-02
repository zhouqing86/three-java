package algorithm.strings;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StringAlgoTest {

    @Test
    public void testLengthOfLongestSubstring() throws Exception {
        assertEquals(3, StringAlgo.lengthOfLongestSubstring("abcabcbb"));
//        assertEquals(1, StringAlgo.lengthOfLongestSubstring("bbbbb"));
//        assertEquals(3, StringAlgo.lengthOfLongestSubstring("pwwkew"));
    }
}
