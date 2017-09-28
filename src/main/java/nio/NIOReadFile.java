package nio;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOReadFile {
    public static void main(String[] args) {
        try {
            FileInputStream fin = new FileInputStream( new File("src/main/resources/testnio.txt" ));
            FileChannel fc = fin.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate( 1024 );
            fc.read( buffer );

            buffer.flip();
            while (buffer.hasRemaining()) {
                byte f = buffer.get();
                System.out.println( (char) f );
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
