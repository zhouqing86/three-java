package medium;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GuavaFileIOTest {
    @Test
    public void testWriteAndReadSmallFiles() throws Exception {
        File file = new File("guava.txt");
        Files.write("Just For Test\nThe second line\nThe Third line".getBytes(), file);
        List<String> lines = Files.readLines(file, Charsets.UTF_8);
        assertEquals(3, lines.size());
        file.delete();
    }

    @Test
    public void testWriteAndReadBigFiles() throws Exception {
        File file = new File("guava.txt");
        if(file.exists()){
            file.delete();
            file.createNewFile();
        }
        for(int i=0; i<10000; i++){
            Files.append("Just For Test\n", file, Charsets.UTF_8);
        }
        LineProcessor<Integer> countProcesser = new LineProcessor<Integer>() {
            private int num;
            @Override
            public boolean processLine(String line) throws IOException {
                num++;
                return true;
            }

            @Override
            public Integer getResult() {
                return num;
            }
        };
        Files.readLines(file, Charsets.UTF_8, countProcesser);
        Integer retInt = countProcesser.getResult();
        file.delete();
        assertEquals(retInt, countProcesser.getResult());
    }

    @Test
    public void testGuavaFileCopy() throws Exception {
        File srcFile = new File("src.txt");
        File destFile = new File("dest.txt");
        Files.write("Just For Test".getBytes(), srcFile);
        Files.copy(srcFile, destFile);
        List<String> lines = Files.readLines(destFile, Charsets.UTF_8);
        srcFile.delete();
        destFile.delete();
        assertEquals(1, lines.size());
        assertEquals("Just For Test", lines.get(0));
    }

    @Test
    public void testGuavaFilesCopy() throws Exception {
        File srcFile = new File("src.txt");
        File destFile = new File("dest.txt");
        Files.write("Just For Test".getBytes(), srcFile);
        Files.copy(srcFile, destFile);
        assertTrue(Files.equal(srcFile,destFile));
    }
}

