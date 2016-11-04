package base;


import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ArrayTest {

    @Test
    public void testInitialArray() throws Exception {
        String [] array = new String []{"abc","def","efg"};
        assertEquals(3, array.length);
        assertEquals("abc",array[0]);

        String [] array2 = new String[5];
        assertEquals(5, array2.length);

        String [] array3 = {"b","c","d"};
        assertEquals(3, array3.length);
    }

    @Test
    public void testPrintArray() throws Exception {
        int [] array = {1,2,3,4,5};
        assertEquals("[1, 2, 3, 4, 5]",Arrays.toString(array));
    }

    @Test
    public void testReverseArray() throws Exception {
        int [] array = {1,2,3,4,5};
        ArrayUtils.reverse(array);
        assertEquals("[5, 4, 3, 2, 1]", Arrays.toString(array));
    }

    @Test
    public void testSortArray() throws Exception {
        int [] array = {5, 4, 3, 2, 1};
        Arrays.sort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    public void testCaseInsensitiveSort() throws Exception {
        String [] array = {"z", "a", "C"};
        Arrays.sort(array,String.CASE_INSENSITIVE_ORDER);
        assertArrayEquals(new String[]{"a", "C", "z"}, array);
    }

    @Test
    public void testRemoveElementFromArray() throws Exception {
        int [] array = {1,3,5};
        int [] array2 = ArrayUtils.removeElement(array,3);
        assertEquals(2, array2.length);
        assertArrayEquals(new int[]{1,5},array2);
    }

    @Test
    public void testArrayToArrayList() throws Exception {
        String [] array = new String []{"abc","def","efg"};
        List strList = Arrays.asList(array);
        assertEquals(3, strList.size());

    }

    @Test
    public void testConcatArrays() throws Exception {
        int [] array = {1,2,3};
        int [] array2 = {4,5,6};
        assertEquals(6, ArrayUtils.addAll(array, array2).length);
    }

    @Test
    public void testArrayContainsAElement() throws Exception {
        String [] array = {"a","b","c"};
        assertTrue(Arrays.asList(array).contains("a"));
    }
}
