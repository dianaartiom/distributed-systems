package commands;

import data.QueueData;
import data.messages.Message;

import java.util.concurrent.BlockingQueue;

public class RouteDirectMessageCommand implements Command {
    private QueueData queueData;
    private Message message;

    public RouteDirectMessageCommand(QueueData queueData, Message message) {
        this.queueData = queueData;
        this.message = message;
    }

    @Override
    public void execute() {
        BlockingQueue<Message> queue = this.queueData.getQueue(message.getRoutingKey());
        queue.add(message);
    }
}
