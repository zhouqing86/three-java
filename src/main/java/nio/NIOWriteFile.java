package nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOWriteFile {
    public static void main(String[] args) {
        try {
            byte[] messages = "hello world!".getBytes();
            FileOutputStream fout = new FileOutputStream( "src/main/resources/writenio.txt" );
            FileChannel fc = fout.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate( 1024 );
            for (int i=0; i<messages.length; ++i) {
                buffer.put( messages[i] );
            }
            buffer.flip();
            fc.write(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
