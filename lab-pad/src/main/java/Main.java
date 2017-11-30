import broker.MessageBroker;
import configuration.BrokerConfigurationIml;

public class Main {

    private static MessageBroker messageBroker;

    public static void main(String[] args) {
        messageBroker = new MessageBroker(new BrokerConfigurationIml());
        messageBroker.start();
    }
}
