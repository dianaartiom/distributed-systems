import messages.Message;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Broadcast {
    private static Client client;
    private static Socket socket;

    public static void main(String[] args) throws IOException {
        client = new Client(getSocket());
        client.start();

        client.setOnMessageReceived((message)-> {
            System.out.println("Server answered: " + message.getResponse());
        });

        new Thread(() -> {
            while (true) {
                try {
//                    System.out.println("sent !");
                    Message message = new Message();
                    message.setCommand(8);
                    message.setRoutingKey("Google.[a-z]*");
                    message.setQueueName("gjl");
                    message.setPayload("wrong");
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
