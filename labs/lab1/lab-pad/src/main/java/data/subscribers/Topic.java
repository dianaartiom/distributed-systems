package data.subscribers;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private static Topic ourInstance = new Topic();
    List<String> topics = new ArrayList<>();

    public List<String> getTopics() {
        return topics;
    }

    public void addTopic(String topic) {
        this.topics.add(topic);
    }

    public static Topic getInstance() {
        return ourInstance;
    }

    private Topic() {
    }
}
