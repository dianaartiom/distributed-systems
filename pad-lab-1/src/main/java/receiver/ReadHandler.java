package receiver;

import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

public class ReadHandler implements CompletionHandler<Integer, Attachment> {
    @Override
    public void completed(Integer result, Attachment attach){

            // Get the buffer ready to read from it
            attach.getBuffer().flip();

            int limits = attach.getBuffer().limit();
            byte bytes[] = new byte[limits];
            attach.getBuffer().get(bytes, 0, limits);
            Charset cs = Charset.forName("UTF-8");
            String msg = new String(bytes, cs);

            // Print the message from the client
            System.out.format("Client at %s says: %s%n", attach.getClientAddr(), msg);

            // Prepare the buffer to be read again
            attach.getBuffer().rewind();

    }

    @Override
    public void failed (Throwable e, Attachment attach){
        e.printStackTrace();
    }
}