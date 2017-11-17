package sender;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Sender {
    public static final String HOST = "localhost";
    public static final int PORT = 6789;
    public static String sentence = "Hello from sender\n!";

    public static void main(String[] args) {
        // Use a try-with-resources to open a channel
        try (AsynchronousSocketChannel channel
                     = AsynchronousSocketChannel.open()) {
            // Connect the client to the server
            String serverName = "localhost";
            int serverPort = 6789;
            SocketAddress serverAddr =
                    new InetSocketAddress(serverName, serverPort);

            Future<Void> result = channel.connect(serverAddr);
            System.out.println("Connecting to the server...");

            // Wait for the connection to complete
            result.get();

            // Connection to the server is complete now
            System.out.println("Connected to the server...");

            // Start reading from and writing to the server
            Attachment attach = new Attachment();
            attach.channel = channel;
            attach.buffer = ByteBuffer.allocate(2048);
            attach.isRead = false;
            attach.mainThread = Thread.currentThread();

            // Place the "Hello" message in the buffer
            Charset cs = Charset.forName("UTF-8");
            String msg = "Hello";
            byte[] data = msg.getBytes(cs);
            attach.buffer.put(data);
            attach.buffer.flip();

            // Write to the server
            ReadWriteHandler readWriteHandler = new ReadWriteHandler();
            channel.write(attach.buffer, attach, readWriteHandler) ;

            // Let this thread wait for ever on its own death until interrupted
            attach.mainThread.join();
        }
        catch (ExecutionException | IOException e) {
            e.printStackTrace();
        }
        catch(InterruptedException e) {
            System.out.println("Disconnected from the server.");
        }
    }
}