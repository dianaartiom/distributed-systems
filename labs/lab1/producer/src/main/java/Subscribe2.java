import messages.Message;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Subscribe2 {

    private static Socket socket;
    private static Client client;

    public static void main(String[] args) {

        client = new Client(getSocket());
        client.start();

        client.setOnMessageReceived((message)-> {
            System.out.println("Server answered: " + message.getResponse());
        });

        new Thread(() -> {
            while (true) {
                try {
                    Message message = new Message();
                    message.setCommand(6);
                    message.setRoutingKey("Google.[a-z]*");
                    message.setQueueName("default");
                    message.setPayload("default");
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
