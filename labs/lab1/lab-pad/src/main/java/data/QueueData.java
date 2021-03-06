package data;

import data.messages.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueueData {
    private Map<String, BlockingQueue<Message>> queues;

    public QueueData() {
        this.queues = new HashMap<>();
    }

    public void createQueue(String queueName){

        queues.put(queueName, new LinkedBlockingDeque<>());
    }

    public BlockingQueue<Message> getQueue(String queueId){
        return queues.get(queueId);
    }

    public List<BlockingQueue<Message>> getQueueByReqExp(String regExp){
        List<BlockingQueue<Message>> selectedQueues = new ArrayList<>();
        Pattern p = Pattern.compile(regExp);
        queues.forEach((queueId, queue) -> {
            if (p.matches(regExp, queueId)){
                selectedQueues.add(queue);
            }
        });
        return selectedQueues;
    }

    public void removeQueue(String queueId){
        if(queues.containsKey(queueId)){
            queues.remove(queueId);
        }
    }
}
