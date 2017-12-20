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

    public GetMessageCommand(QueueData queueData, MessageReceivedFromClient messageReceivedFromClient) {
        this.queueData = queueData;
        this.message = messageReceivedFromClient.getMessage();
        this.client = messageReceivedFromClient.getClient();
    }

    @Override
    public void execute() {
        BlockingQueue<Message> queue = this.queueData.getQueue(message.getQueueName());
        Message message = new Message();
        if (queue == null) {
            message.setResponse("No queue such queue.");
            this.client.write(message);
            return;
        }
        if (queue.size() > 0) {
            message = queue.poll();
            message.setResponse("Message extracted from queue: " + message.getPayload());
            this.client.write(message);
            return;
        }
        message.setResponse("No messages in queue.");
        this.client.write(message);
    }
}
