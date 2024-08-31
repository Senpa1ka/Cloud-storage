import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class FileTransfer {

    public static void main(File file) throws IOException {

        System.out.println("file lenght=" + file.length());

        FileChannel fileChannel = new FileInputStream(file).getChannel();
        SocketAddress socketAddress = new InetSocketAddress("localhost", 9999);
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(socketAddress);

        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(file.length());
        buffer.flip();
        socketChannel.write(buffer);

        long total = 0;
        while (total < file.length()) {
            long transferred = fileChannel.transferTo(total, file.length()-total, socketChannel);
            total += transferred;
        }

        buffer.clear();
        socketChannel.read(buffer);
        buffer.flip();
        long lenght = buffer.getLong();
        System.out.println("bytes received by server = " + lenght);

        socketChannel.finishConnect();
    }
}
