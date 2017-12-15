import messages.Message;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class QueueRemover {

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
//                    System.out.println("sent !");
                    Message message = new Message();
                    message.setCommand(1);
                    message.setRoutingKey("aaaa");
                    message.setQueueName("arst");
                    message.setPayload("qwfpgj");
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
