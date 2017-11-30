package commands;

import broker.Client;
import broker.MessageReceivedFromClient;
import data.QueueData;
import data.messages.Message;

import java.util.concurrent.BlockingQueue;

public class GetMessageCommand implements Command {
    private QueueData queueData;
    private Message message;
    private Client client;

    GetMessageCommand(QueueData queueData, MessageReceivedFromClient messageReceivedFromClient) {
        this.queueData = queueData;
        this.message = messageReceivedFromClient.getMessage();
        this.client = messageReceivedFromClient.getClient();
    }

    @Override
    public void execute() {
        BlockingQueue<Message> queue = this.queueData.getQueue(message.getQueueName());
        Message message = queue.poll();
        this.client.write(message);
    }
}
