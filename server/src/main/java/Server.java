import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {

    public static void main(String[] args) throws IOException {
    final File outputFile = new File("text.txt");

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        SocketChannel socketChannel = serverSocketChannel .accept();

        ByteBuffer buffer = ByteBuffer.allocate(8);
        socketChannel.read(buffer);
        buffer.flip();
        long lenght = buffer.getLong();

        FileChannel fileChannel = new FileOutputStream(outputFile).getChannel();

        long total = 0;

        while (total < lenght) {
            long transferred = fileChannel.transferFrom(socketChannel, total, lenght - total);
            if (transferred <= 0) {
                break;
            }
total += transferred;
        }

buffer.clear();
        buffer.putLong(total);
        buffer.flip();
        socketChannel.write(buffer);

        serverSocketChannel.close();

        System.out.println("lenght received= " + lenght + ", saved file lenght= " + outputFile.length());



    }
}
