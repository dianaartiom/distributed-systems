package commands;

import broker.Client;
import broker.MessageReceivedFromClient;
import data.QueueData;
import data.messages.Message;
import data.subscribers.Subscriber;
import data.subscribers.Subscribers;
import data.subscribers.Topic;

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
        Message message = new Message();

        // check if the subscriber does not exist
        if (Subscribers.getINSTANCE().subscriberExists(client)) {
            message.setResponse("Client already subscribed.");
        } else {
            Subscriber subscriber = new Subscriber(this.client, this.message.getQueueName());
            Subscribers.getINSTANCE().appendSubscriber(subscriber);
            message.setResponse("Client succesfully subscribed.");
        }

        // add the topic if it was not added eralier
        if (!Topic.getInstance().getTopics().contains(this.message.getQueueName())) {
            Topic.getInstance().addTopic(this.message.getQueueName());
        }
        client.write(message);
    }
}
