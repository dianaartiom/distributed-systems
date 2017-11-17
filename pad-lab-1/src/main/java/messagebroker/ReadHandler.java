package messagebroker;

import db.SubscriberRepository;
import model.Subscriber;
import model.XmlObject;
import utils.StringToDom;
import utils.SubscriberValidator;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

public class ReadHandler implements CompletionHandler<Integer, Attachment> {
    @Override
    public void completed(Integer result, Attachment attachment) {
        attachment.getBuffer().flip();
        int limits = attachment.getBuffer().limit();
        byte bytes[] = new byte[limits];
        attachment.getBuffer().get(bytes, 0, limits);
        attachment.getBuffer().rewind();
        Charset cs = Charset.forName("UTF-8");
        String msg = new String(bytes, cs);
        XmlObject xmlObject = null;
        try {
            xmlObject = (XmlObject) StringToDom.getObjectFromXmlString(XmlObject.class,msg);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        WriteHandler writer = new WriteHandler();

        Subscriber subs = new Subscriber();
        subs.setName(xmlObject.getName());
        if(xmlObject.getSubscriber()&&!SubscriberValidator.check(xmlObject)) {
            subs.setHost(xmlObject.getHost());
            subs.setPort(xmlObject.getPort());
            SubscriberRepository.getInstance().add(subs);
        }else if (xmlObject.getSubscriber()) {
            try {
                writer.SendAuthError(xmlObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println(xmlObject);


        try {
            writer.SendToAllSubscribers(xmlObject);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void failed(Throwable e, Attachment attachment) {
        e.printStackTrace();
    }
}