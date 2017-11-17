package messagebroker;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;

public class MessageBroker {

    public static void main(String[] args) throws IOException, InterruptedException {
        MessageBroker messageBroker = new MessageBroker();
    }
    public MessageBroker() throws IOException, InterruptedException {
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(null);
        server.bind(new InetSocketAddress("localhost",6789));
        Attachment attachment = new Attachment();
        attachment.setServer(server);
        server.accept(attachment,new ConnectionHandler());
        Thread.currentThread().join();
    }
}
