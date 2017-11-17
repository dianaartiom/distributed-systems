package sender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

public class ReadWriteHandler implements CompletionHandler<Integer, Attachment> {
    @Override
    public void completed(Integer result, Attachment attach) {
        if (attach.isRead) {
            attach.buffer.flip();

            // Get the text read from the server
            Charset cs = Charset.forName("UTF-8");

            int limits = attach.buffer.limit();
            byte bytes[] = new byte[limits];
            attach.buffer.get(bytes, 0, limits);
            String msg = new String(bytes, cs);

            // A read from the server was completed
            System.out.format("Server Responded: %s%n", msg);

            // Prompt the user for another message
            msg = this.getTextFromUser();
            if (msg.equalsIgnoreCase("bye")) {
                // Interrupt the main thread, so the program terminates
                attach.mainThread.interrupt();
                return;
            }

            // Prepare buffer to be filled in again
            attach.buffer.clear();
            byte[] data = msg.getBytes(cs);
            attach.buffer.put(data);

            // Prepared buffer to be read
            attach.buffer.flip();

            attach.isRead = false; // It is a write

            // Write to the server
            attach.channel.write(attach.buffer, attach, this);
        }

        else {
            // A write to the server was completed. Perform another
            // read from the server
            attach.isRead = true;

            // Prepare the buffer to be filled in
            attach.buffer.clear();

            // Read from the server
            attach.channel.read(attach.buffer, attach, this);
        }
    }

    @Override
    public void failed(Throwable e, Attachment attach) {
        e.printStackTrace();
    }

    private String getTextFromUser() {
        System.out.print("Please enter a message (Bye to quit):");
        String msg = null;

        BufferedReader consoleReader =
                new BufferedReader(new InputStreamReader(System.in));
        try {
            msg = consoleReader.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return msg;
    }
}
