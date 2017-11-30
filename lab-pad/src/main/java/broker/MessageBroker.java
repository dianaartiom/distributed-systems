package broker;

import commands.Command;
import commands.CommandFactory;
import configuration.BrokerConfiguration;
import data.QueueData;

import java.util.ArrayList;
import java.util.List;

public class MessageBroker {

    private List<Client> clients = new ArrayList<>();

    private QueueData queueData;
    private CommandFactory commandFactory;
    private ConnectionListener connectionListener;

    public MessageBroker(BrokerConfiguration configuration) {
        queueData = new QueueData();
        commandFactory = new CommandFactory(queueData);
        connectionListener = new ConnectionListener(configuration.getBrokerInetSocketAddress());
        connectionListener.setOnConnectorAccepted(this::onClientAccepted);
    }

    private void onClientAccepted(Client client) {
        client.setOnMessageReceived(this::onMessageReceived);
        client.start();
        System.out.println("New client connected");
        clients.add(client);
    }

    private void onMessageReceived(MessageReceivedFromClient message) {
        Command command = commandFactory.getCommandForMessage(message);
        if (command != null) {
            command.execute();
        }
    }

    public void start() {
        connectionListener.start();
    }
}
