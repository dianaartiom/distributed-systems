package commands;

import broker.Client;
import broker.MessageReceivedFromClient;
import data.QueueData;
import data.messages.Message;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class RouteTopicMessageCommand implements Command {
    private QueueData queueData;
    private Message message;
    private Client client;

    public RouteTopicMessageCommand(QueueData queueData, MessageReceivedFromClient messageReceivedFromClient) {
        this.queueData = queueData;
        this.message = messageReceivedFromClient.getMessage();
        this.client = messageReceivedFromClient.getClient();
    }

    @Override
    public void execute() {
        this.queueData.getQueueByReqExp(message.getRoutingKey())
                .forEach(queue -> queue.add(message.clone())); /* treb oare clone??? */
        // todo pridumai the logic
        Message tempMessage = this.message.clone();
        tempMessage.setResponse(message.getPayload());
        client.write(tempMessage);

        //todo implement message prototype and enqueue different insatances of objects.
    }
}
