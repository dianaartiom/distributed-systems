package db;

import model.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class SubscriberRepository {

    private static SubscriberRepository ourInstance = new SubscriberRepository();

    public static SubscriberRepository getInstance() {
        return ourInstance;
    }

    private List<Subscriber> subscribers = new ArrayList<>();

    private SubscriberRepository() {

    }

    public void add(Subscriber subs) {
        subscribers.add(subs);
    }

    public String getList() {
        String list = "";
        synchronized (list) {
            for (Subscriber subscriber : this.subscribers) {
                list += subscriber.toString() + "\n";
            }
        }
        return list;
    }

    public List<Subscriber> getData(){
        return this.subscribers;
    }
}
