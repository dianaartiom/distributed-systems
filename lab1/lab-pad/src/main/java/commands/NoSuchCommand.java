package commands;

import broker.Client;
import broker.MessageReceivedFromClient;
import data.QueueData;
import data.messages.Message;

public class NoSuchCommand implements Command {
    private QueueData queueData;
    private Message message;
    private Client client;

    NoSuchCommand(QueueData queueData, MessageReceivedFromClient messageReceivedFromClient) {
        this.queueData = queueData;
        this.message = messageReceivedFromClient.getMessage();
        this.client = messageReceivedFromClient.getClient();
    }

    @Override
    public void execute() {
        this.message.setResponse("No such command. Operation aborted.");
        client.write(this.message);
    }
}
