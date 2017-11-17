package messagebroker;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class ConnectionHandler implements CompletionHandler<AsynchronousSocketChannel, Attachment> {
    @Override
    public void completed(AsynchronousSocketChannel client, Attachment attach) {
        try {
            // Get the client address
            SocketAddress clientAddr = client.getRemoteAddress();

            System.out.format("Accepted a connection from %s%n", clientAddr);

            // Accept another connection
            attach.getServer().accept(attach, this);

            // Handle the client connection by invoking an asyn read
            Attachment newAttach = new Attachment();
            newAttach.setServer(attach.getServer());
            newAttach.setClient(client);
            newAttach.setBuffer(ByteBuffer.allocate(2048));
            newAttach.setRead(true);
            newAttach.setClientAddr(clientAddr);

            // Create a new completion handler for reading to and writing
            // from the new client
            // Read from the client
            client.read(newAttach.getBuffer(), newAttach, new ReadWriteHandler());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void failed(Throwable throwable, Attachment attachment) {

    }
}
