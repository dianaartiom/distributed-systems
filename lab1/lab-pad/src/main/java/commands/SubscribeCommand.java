package commands;

import broker.Client;
import broker.MessageReceivedFromClient;
import data.QueueData;
import data.messages.Message;
import data.subscribers.Subscribers;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class SubscribeCommand implements Command {
    private QueueData queueData;
    private Message message;
    private Client client;

    public SubscribeCommand(QueueData queueData, MessageReceivedFromClient messageReceivedFromClient) {
        this.queueData = queueData;
        this.message = messageReceivedFromClient.getMessage();
        this.client = messageReceivedFromClient.getClient();
    }

    @Override
    public void execute() {
        List<BlockingQueue<Message>> blockingMessages = this.queueData.getQueueByReqExp(message.getRoutingKey());

        for (BlockingQueue<Message> queu:
             blockingMessages) {
            Message message = queu.poll();
            message.setResponse("Was retreived from queue " + message.getQueueName());

            client.write(message);
        }


        Subscribers.getINSTANCE().appendSubscriber(this.client, this.message.getQueueName());
        this.queueData.getQueueByReqExp(message.getRoutingKey())
                .forEach(message -> message.poll().clone());
    }
}
