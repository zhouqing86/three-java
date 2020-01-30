package guavabook;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JoinerTest {
    @Test
    public void testJoiner() throws Exception {
        Joiner stringJoiner = Joiner.on("|").skipNulls();
        List<String> stringList = Lists.newArrayList("a", "b", "c", null);
        assertEquals("a|b|c", stringJoiner.join(stringList));


        stringJoiner = Joiner.on("|").useForNull("missing");
        assertEquals("a|b|c|missing", stringJoiner.join(stringList));
    }

    @Test
    public void testFileWriterWithJoiner() throws Exception {
        Joiner stringJoiner = Joiner.on("|").useForNull("missing");
        List<String> stringList = Lists.newArrayList("a", "b", "c", null);
        FileWriter fileWriter = new FileWriter(new File("joiner_test.txt"));
        stringJoiner.appendTo(fileWriter, stringList);
        fileWriter.close();
    }

    @Test
    public void testMapJoiner() throws Exception {
        HashMap<Object, Object> hashMap = Maps.newHashMap();
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");

        assertEquals("key1=value1&key2=value2", Joiner.on("&").withKeyValueSeparator("=").join(hashMap));
    }
}
