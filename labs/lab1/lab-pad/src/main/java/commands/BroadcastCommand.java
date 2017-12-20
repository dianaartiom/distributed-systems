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

public class BroadcastCommand implements Command {
    private QueueData queueData;
    private Message message;
    private Client client;

    public BroadcastCommand(QueueData queueData, MessageReceivedFromClient messageReceivedFromClient) {
        this.queueData = queueData;
        this.message = messageReceivedFromClient.getMessage();
        this.client = messageReceivedFromClient.getClient();
    }

    @Override
    public void execute() {
        Message message = new Message();

        List<BlockingQueue<Message>> list = this.queueData.getQueueByReqExp(this.message.getRoutingKey());
        if (list != null) {
            for (BlockingQueue<Message> queue :
                    list) {
                message = queue.poll();
                for (int i = 0; i < Subscribers.getINSTANCE().getSubscribers().size(); i++) {
                    if (Subscribers.getINSTANCE().getSubscribers()
                            .get(i).getTopic().equals(this.message.getRoutingKey())) {
                        message.setResponse("Subscriber found. Sending message.");
                        Subscribers.getINSTANCE().getSubscribers()
                                .get(i).getClient().write(message);
                    }
                }
            }
        }




//        if (Topic.getInstance().getTopics().contains(this.message.getQueueName())) {
//            if (queueData.getQueue(this.message.getQueueName()) != null) {
//                message = queueData.getQueue(this.message.getQueueName()).poll();
//                message.setResponse("Message successfully broadcasted.");
//                // iterate through all subscribers and write the message
//                for (Subscriber s :
//                        Subscribers.getINSTANCE().getSubscribers()) {
//                    s.getClient().write(message);
//                    System.out.println(s);
//                }
//            } else {
//                message.setResponse("No such queue.");
//            }
//        }
//        client.write(message);
    }
}
