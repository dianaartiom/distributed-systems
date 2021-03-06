import messages.Message;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class RouteDirectMessage {
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
                    Message message = new Message();
                    message.setCommand(2);
                    message.setRoutingKey("Google.arst");
                    message.setQueueName("Google.arst");
                    message.setPayload("papalsea");
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
