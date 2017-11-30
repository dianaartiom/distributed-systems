package configuration;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class BrokerConfigurationIml implements BrokerConfiguration {
    @Override
    public InetSocketAddress getBrokerInetSocketAddress() {
        try {
            return new InetSocketAddress(InetAddress.getLocalHost(), 8000);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
