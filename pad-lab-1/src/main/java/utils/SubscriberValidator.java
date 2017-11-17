package utils;

import db.SubscriberRepository;
import model.Subscriber;
import model.XmlObject;

import java.util.Objects;

public class SubscriberValidator {
    public static Boolean check(XmlObject xmlobject){
        for (Subscriber subscriber : SubscriberRepository.getInstance().getData()) {
            if(Objects.equals(subscriber.getName(), xmlobject.getName())) return true;
        }
        return false;
    }
}
