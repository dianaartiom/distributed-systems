package model;

public class Subscriber {

    private String name;
    private String host;
    private Long port;

    public Subscriber(String name, String host, Long port) {
        this.name = name;
        this.host = host;
        this.port = port;
    }

    public Subscriber() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Long port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }

    public Long getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "Subscriber( name=" + this.name + " )";
    }

}
