package messagebroker;

import java.io.IOException;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

public class ReadWriteHandler implements CompletionHandler<Integer, Attachment> {
        @Override
        public void completed(Integer result, Attachment attach){
        if (result == -1) {
            try {
                attach.getClient().close();
                System.out.format("Stopped listening to the client %s%n",
                        attach.getClientAddr());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return;
        }

        if (attach.isRead()) {
            // A read to the client was completed

            // Get the buffer ready to read from it
            attach.getBuffer().flip();

            int limits = attach.getBuffer().limit();
            byte bytes[] = new byte[limits];
            attach.getBuffer().get(bytes, 0, limits);
            Charset cs = Charset.forName("UTF-8");
            String msg = new String(bytes, cs);

            // Print the message from the client
            System.out.format("Client at %s says: %s%n", attach.getClientAddr(), msg);

            // Let us echo back the same message to the client
            attach.setRead(false); // It is a write

            // Prepare the buffer to be read again
            attach.getBuffer().rewind();

            // Write to the client again
            attach.getClient().write(attach.getBuffer(), attach, this);
        } else {
            // A write to the client was completed.
            // Perform another read from the client
            attach.setRead(true);

            // Prepare the buffer to be filled in
            attach.getBuffer().clear();

            // Perform a read from the client
            attach.getClient().read(attach.getBuffer(), attach, this);
        }

    }

    @Override
    public void failed (Throwable e, Attachment attach){
        e.printStackTrace();
    }
}