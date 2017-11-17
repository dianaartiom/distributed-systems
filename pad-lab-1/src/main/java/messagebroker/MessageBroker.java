package messagebroker;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;

public class MessageBroker {

    public MessageBroker() {
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        try (AsynchronousServerSocketChannel server =
                     AsynchronousServerSocketChannel.open()) {
            // Bind the server to the localhost and the port 8989
            String host = "localhost";
            int port = 6789;
            InetSocketAddress sAddr =
                    new InetSocketAddress(host, port);
            server.bind(sAddr);

            // Display a message that server is ready
            System.out.format("Server is listening at %s%n", sAddr);

            // Prepare the attachment
            Attachment attach = new Attachment();
            attach.setServer(server);

            // Accept new connections
            server.accept(attach, new ConnectionHandler());

            try {
                // Wait until the main thread is interrupted
                Thread.currentThread().join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() throws IOException, InterruptedException {
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(null);
        server.bind(new InetSocketAddress("localhost", 6789));


        Attachment attachment = new Attachment();
        attachment.setServer(server);

        server.accept(attachment, new ConnectionHandler());

        // Wait indefinitely until someone interrups the main thread
        Thread.currentThread().join();
    }
}
