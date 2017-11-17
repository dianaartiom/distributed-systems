package messagebroker;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;

/**
 * An object of the following Attachment class will be used to serve as an attachment
 * to the completion handler. An attachment object is used to pass the context for
 * the server socket that may be used inside the
 * completed() and failed() methods of the completion handler.
 */

public class Attachment {
    private AsynchronousServerSocketChannel server;
    private AsynchronousSocketChannel client;
    private ByteBuffer buffer;
    private SocketAddress clientAddr;
    private boolean isRead;

    /**
     * Empty constructor
     */
    public Attachment() {

    }

    /**
     * Getter for server field in Attachment class
     * @return server field
     */
    public AsynchronousServerSocketChannel getServer() {
        return server;
    }


    /**
     * Getter for client field in Attachment class
     * @return client field
     */
    public AsynchronousSocketChannel getClient() {
        return client;
    }


    /**
     * Getter for buffer field in Attachment class
     * @return buffer field
     */
    public ByteBuffer getBuffer() {
        return buffer;
    }

    /**
     * Sets the value for server member field
     * @param server - to be linked to the server member field
     */
    public void setServer(AsynchronousServerSocketChannel server) {
        this.server = server;
    }

    /**
     * Sets the value for client member field
     * @param client - to be linked to the client member field
     */
    public void setClient(AsynchronousSocketChannel client) {
        this.client = client;
    }

    /**
     * Sets the value for buffer member field
     * @param buffer - to be linked to the buffer member field
     */
    public void setBuffer(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    public void setClientAddr(SocketAddress clientAddr) {
        this.clientAddr = clientAddr;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public SocketAddress getClientAddr() {
        return clientAddr;
    }

    public boolean isRead() {
        return isRead;
    }
}
