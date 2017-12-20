package data.subscribers;

import broker.Client;

import java.util.regex.Pattern;

public class Subscriber {
    Client client;
    String topic;

    public Subscriber(Client client, String topic) {
        this.client = client;
        this.topic = topic;
    }

    public boolean matchesRegex(String qname) {
        Pattern p = Pattern.compile(this.topic);
        if (p.matches(this.topic, qname)) {
            return true;
        }
        return false;
    }

    public Client getClient() {
        return client;
    }

    public String getTopic() {
        return topic;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
