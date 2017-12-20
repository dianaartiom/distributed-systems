import broker.Client;
import data.messages.Message;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GetMessageCommandTest {
    private static Socket socket;
    private static Client client;

    @Test
    public void getMessageFromQueueSuccess() {
        client = new Client(getSocket());
        client.start();

        client.setOnMessageReceived((message)-> {
            System.out.println("Server answered: " + message.getResponse());
            assertThat(message.getResponse(), is("de inserat textul"));
        });

        new Thread(() -> {
            while (true) {
                try {
                    Message message = new Message();
                    message.setCommand(4);
                    message.setRoutingKey("aaaa");
                    message.setQueueName("Google.arst");
                    message.setPayload("arst");
                    client.write(message);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static Socket getSocket() {
        socket = null;
        try {
            socket = new Socket(InetAddress.getLocalHost(), 8000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }
}
