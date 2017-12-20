package commands;

import broker.Client;
import broker.MessageReceivedFromClient;
import data.QueueData;
import data.messages.Message;

import java.util.concurrent.BlockingQueue;

public class RouteDirectMessageCommand implements Command {
    private QueueData queueData;
    private Message message;
    private Client client;

    public RouteDirectMessageCommand(QueueData queueData, MessageReceivedFromClient messageReceivedFromClient) {
        this.queueData = queueData;
        this.message = messageReceivedFromClient.getMessage();
        this.client = messageReceivedFromClient.getClient();
    }

    @Override
    public void execute() {
        String queueName = message.getRoutingKey();
        if (this.queueData.getQueue(queueName) != null) {
            this.queueData.getQueue(queueName).add(message);
            this.message.setResponse("Message successfully added to queue " + queueName);
            client.write(message);
            return;
        }
        this.message.setResponse("Routing key could not be found.");
        client.write(message);
    }
}
