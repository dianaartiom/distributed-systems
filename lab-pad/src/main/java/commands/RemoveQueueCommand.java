package commands;

import broker.Client;
import broker.MessageReceivedFromClient;
import data.QueueData;
import data.messages.Message;

public class RemoveQueueCommand implements Command {
    private QueueData queueData;
    private Message message;
    private Client client;

    public RemoveQueueCommand(QueueData queueData, MessageReceivedFromClient messageReceivedFromClient) {
        this.queueData = queueData;
        this.message = messageReceivedFromClient.getMessage();
        this.client = messageReceivedFromClient.getClient();
    }

    @Override
    public void execute() {
        String queueID = this.message.getQueueName();
        if (this.queueData.getQueue(queueID) != null) {
            queueData.removeQueue(queueID);
            this.message.setResponse("Queue successfully deleted.");
            this.client.write(message);
            return;
        }
        this.message.setResponse("No such queue.");
        this.client.write(message);
    }
}
