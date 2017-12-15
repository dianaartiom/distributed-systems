package data.subscribers;

import broker.Client;

public class Subscriber {
    Client client;
    String topic;

    public Subscriber(Client client, String topic) {
        this.client = client;
        this.topic = topic;
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
