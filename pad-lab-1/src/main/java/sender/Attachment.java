package sender;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

public class Attachment {
    AsynchronousSocketChannel channel;
    ByteBuffer buffer;
    Thread mainThread;
    boolean isRead;
}