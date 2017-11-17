package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XmlObject {

    private Boolean isSubscriber;
    private String name;
    private String message;
    private String host;
    private Long port;

    private XmlObject() {
        this.name = "";
        this.message = "";
        this.host = "";
        this.port = null;
    }

    public XmlObject(Boolean isSubscriber, String name, String message, String host, Long port) {
        this.isSubscriber = isSubscriber;
        this.name = name;
        this.message = message;
        this.host = host;
        this.port = port;
    }

    public Boolean getSubscriber() {
        return isSubscriber;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getHost() {
        return host;
    }

    public Long getPort() {
        return port;
    }

    public void setSubscriber(Boolean subscriber) {
        isSubscriber = subscriber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Long port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "XmlObject( isSubscribe = " + isSubscriber +
                ", name = "+ name +
                ", message = "+ message +
                ", host = "+ host +
                ", port = "+ port +
                " )";
    }
}
