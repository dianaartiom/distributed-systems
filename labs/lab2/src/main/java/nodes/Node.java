package nodes;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.ArrayList;

public class Node implements Serializable {

    private InetSocketAddress location;
    private String message;
    private ArrayList<InetSocketAddress> linksAdresses;

    public Node(InetSocketAddress inetSocketAddress ) {
        this.location = inetSocketAddress;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setLocation(InetSocketAddress location) {
        this.location = location;
    }

    public void setLinksAdresses(ArrayList<InetSocketAddress> linksAdresses) {
        this.linksAdresses = linksAdresses;
    }

    public InetSocketAddress getLocation() {
        return location;
    }

    public ArrayList<InetSocketAddress> getLinksAdresses() {
        return linksAdresses;
    }


    @Override
    public String toString() {
        return "Node{" +
                "location=" + location +
                ", message='" + message + '\'' +
                ", linksAdresses=" + linksAdresses +
                '}';
    }
}
