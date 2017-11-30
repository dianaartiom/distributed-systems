
import messages.Message;
import utils.MessageConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.function.Consumer;


public class Client {
    private Consumer<Message> onMessageReceived;
    private BufferedReader in;
    private PrintWriter outputStream;

    Client(Socket connectorSocket) {
        try {
            this.in =  new BufferedReader(new InputStreamReader(connectorSocket.getInputStream()));
            this.outputStream =  new PrintWriter(connectorSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void write(Message message) {
        MessageConverter messageConverter = new MessageConverter();
        String msg = messageConverter.toGson(message);
        if (outputStream != null) {
            outputStream.println(msg);
        }
    }

    void start() {
        new Thread(this::read).start();
    }

    private void read() {
        while (true) {
            String msg = null;
            try {
                msg = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            MessageConverter messageConverter = new MessageConverter();
            Message messageObj = messageConverter.toObj(msg);

            if (onMessageReceived != null) {
                onMessageReceived.accept(messageObj);
            }
        }
    }

    public void setOnMessageReceived(Consumer<Message> onMessageReceived) {
        this.onMessageReceived = onMessageReceived;
    }
}
