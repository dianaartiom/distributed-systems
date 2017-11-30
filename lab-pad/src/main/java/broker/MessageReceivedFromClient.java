package broker;

import data.messages.Message;

public class MessageReceivedFromClient {
    private Client client;
    private Message message;

    public MessageReceivedFromClient(Client client, Message message) {
        this.client = client;
        this.message = message;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
