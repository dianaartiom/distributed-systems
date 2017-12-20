package data.subscribers;


import broker.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Subscribers {
    private static Subscribers INSTANCE = new Subscribers();
    private List<Subscriber> subscribers = new ArrayList<>();

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



    public void appendSubscriber(Subscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    public boolean subscriberExists(Client client) {
        for (Subscriber su :
                this.subscribers) {
            if (su.getClient() == client) {
                return true;
            }
        }
        return false;
    }
}
