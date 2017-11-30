package commands;

import broker.MessageReceivedFromClient;
import data.QueueData;
import data.messages.Message;
import broker.Client;

public class CreateQueueCommand implements Command {
    private QueueData queueData;
    private Message message;
    private Client client;

    CreateQueueCommand(QueueData queueData, MessageReceivedFromClient messageReceivedFromClient) {
        this.queueData = queueData;
        this.message = messageReceivedFromClient.getMessage();
        this.client = messageReceivedFromClient.getClient();
    }

    @Override
    public void execute() {
        String queueName = message.getQueueName();
        if (queueData.getQueue(queueName) != null) {
            this.message.setResponse("Error. This queue already exists.");
            this.client.write(this.message);
            return;
        }
        message.setResponse("Queue successfully created.");
        queueData.createQueue(this.message.getQueueName());
        this.client.write(this.message);
    }
}
