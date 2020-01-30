package guavabook;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SpiltterTest {
    @Test
    public void testNormalSplit() throws Exception {
        Iterable<String> strings = Splitter.on("|").split("a|b|c");
        assertEquals(Lists.newArrayList("a", "b", "c"), Lists.newArrayList(strings));


        strings = Splitter.on("|").trimResults().split("||a|b|c||");
        assertEquals(Lists.newArrayList("", "", "a", "b", "c", "", ""), Lists.newArrayList(strings));
    }


    @Test
    public void testMapSplit() throws Exception {
        Splitter.MapSplitter mapSplitter = Splitter.on("&").withKeyValueSeparator("=");
        Map<String, String> split = mapSplitter.split("key1=value1&key2=value2");

        HashMap<Object, Object> hashMap = Maps.newHashMap();
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        assertEquals(hashMap, split);
    }
}
