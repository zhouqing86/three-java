package base;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class JavaIOTest {
    @Test
    public void testInputStream() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("test.txt");
        System.out.println(IOUtils.toString(input, "UTF-8"));
    }


    @Test
    public void testCreateFile() throws Exception {
        File file = new File("test.txt");
        assertEquals("test.txt",file.getPath());
        if (!file.exists()) {
            file.createNewFile();
        } else {
            file.delete();
        }
    }


    @Test
    public void testBufferedInputStream() throws Exception {
        byte [] expected = "just for test".getBytes();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("test.txt");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(input);
        int i = 0;
        while (bufferedInputStream.available() > 0) {
            assertEquals(expected[i], bufferedInputStream.read());
            i++;
        }
    }


    @Test
    public void testBufferedReader() throws Exception {
        String myString = "Just For Test\nthe second line";
        InputStream is = new ByteArrayInputStream(myString.getBytes("UTF-8"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        assertEquals("Just For Test", bufferedReader.readLine());
    }


    @Test
    public void testFileOutputStream() throws Exception {
        File file = new File("test.txt");
        String myString = "Just For Test\nthe second line";
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        if (!file.exists()) {
            file.createNewFile();
        }

        fileOutputStream.write(myString.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();

        Scanner scanner = new Scanner(file);
        assertEquals("Just For Test", scanner.nextLine());

        if(file.exists()) {
            file.delete();
        }
    }


    @Test
    public void testBufferedWriter() throws Exception {
        File file = new File("test.txt");
        String myString = "Just For Test\nthe second line";
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(myString);
        bufferedWriter.close();

        Scanner scanner = new Scanner(file);
        assertEquals("Just For Test", scanner.nextLine());

        if(file.exists()) {
            file.delete();
        }
    }


    @Test
    public void testPrintWriter() throws Exception {
        //PrintWriter gives you more flexibility. Using this you can easily format the content which is to be appended to the File
        File file = new File("test.txt");
        String myString = "Just For Test\nthe second line";
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        PrintWriter printWriter  = new PrintWriter(bufferedWriter);
        printWriter.println("ni hao ma");
        printWriter.println("wo hen hao");
        printWriter.close();

        Scanner scanner = new Scanner(file);
        assertEquals("ni hao ma", scanner.nextLine());

        if(file.exists()) {
            file.delete();
        }
    }
}
