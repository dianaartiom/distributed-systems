import broker.Client;
import broker.MessageBroker;
import configuration.BrokerConfigurationIml;
import data.messages.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DeleteQueueCommandTest {

    private static Socket socket;
    private static Client client;
    private static MessageBroker messageBroker;

    @Before public void initialize() {
        this.messageBroker = new MessageBroker(new BrokerConfigurationIml());
        messageBroker.start();
    }


    @Test public
    void deleteQueueSuccess() {
        client = new Client(getSocket());
        client.start();

        client.setOnMessageReceived((message)-> {
            System.out.println("Server answered: " + message.getResponse());
            assertThat(message.getResponse(), is("Queue successfully deleted."));
        });

        new Thread(() -> {
            while (true) {
                try {
//                    System.out.println("sent !");
                    Message message = new Message();
                    message.setCommand(1);
                    message.setQueueName("arst");
                    client.write(message);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Test public void deleteNotExistingQueueTest() {
        client = new Client(getSocket());
        client.start();

        client.setOnMessageReceived((message)-> {
            System.out.println("Server answered: " + message.getResponse());
            assertThat(message.getResponse(), is("No such queue."));
        });

        new Thread(() -> {
            while (true) {
                try {
//                    System.out.println("sent !");
                    Message message = new Message();
                    message.setCommand(1);
                    message.setQueueName("789");
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
