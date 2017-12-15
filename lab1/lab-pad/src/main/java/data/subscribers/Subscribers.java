package data.subscribers;


import broker.Client;
import java.util.List;

public class Subscribers {
    private static Subscribers INSTANCE = new Subscribers();
    private List<Subscriber> subscribers;

    private Subscribers() {}

    public static Subscribers getINSTANCE() {
        return INSTANCE;
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public void appendSubscriber(Client client, String topic) {
        // verifica daca exist deja acest subscriber
//        if (this.subscribers.)
        this.subscribers.add(new Subscriber(client, topic));
    }
}
