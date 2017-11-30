package commands;

import data.QueueData;
import data.messages.Message;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class RouteTopicMessageCommand implements Command {
    private QueueData queueData;
    private Message message;

    public RouteTopicMessageCommand(QueueData queueData, Message message) {
        this.queueData = queueData;
        this.message = message;
    }

    @Override
    public void execute() {
        this.queueData.getQueueByReqExp(message.getRoutingKey())
                .forEach(queue -> queue.add(message));

        //todo implement message prototype and enqueue different insatances of objects.
    }
}
