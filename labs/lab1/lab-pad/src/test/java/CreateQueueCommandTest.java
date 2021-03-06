import broker.Client;
import broker.MessageBroker;
import configuration.BrokerConfigurationIml;
import data.messages.Message;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CreateQueueCommandTest {

    private static Socket socket;
    private static Client client;
    private static MessageBroker messageBroker;

    @Before
    public void initialize() {
        this.messageBroker = new MessageBroker(new BrokerConfigurationIml());
        messageBroker.start();
        client = new Client(getSocket());
        client.start();
    }

    @Test
    public void createQueueShouldSucceed() {

        client.setOnMessageReceived((message)-> {
            System.out.println("Server answered: " + message.getResponse());
            assertThat(message.getMessage(), is("Queue successfully created."));
        });

        new Thread(() -> {
            while (true) {
                try {
//                    System.out.println("sent !");
                    Message message = new Message();
                    message.setCommand(0);
                    message.setQueueName("arst");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Test public void existingQueueTest() {
        client.setOnMessageReceived((message)-> {
            System.out.println("Server answered: " + message.getResponse());
            assertThat(message.getMessage(), is("Error. This queue already exists."));
        });

        new Thread(() -> {
            while (true) {
                try {
//                    System.out.println("sent !");
                    Message message = new Message();
                    message.setCommand(0);
                    message.setQueueName("arst");
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
