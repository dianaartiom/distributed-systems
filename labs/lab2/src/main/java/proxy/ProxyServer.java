package proxy;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ProxyServer {
    private DatagramSocket socket;
    private InetAddress group;
    private byte[] buf;

    public static void main(String[] args) throws IOException {
        ProxyServer proxyServer = new ProxyServer();
        proxyServer.multicast("arst");
    }

    public void multicast(String multicastMessage)
            throws IOException {
        socket = new DatagramSocket();
        group = InetAddress.getByName("230.0.0.0");
        buf = multicastMessage.getBytes();

        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, group, 4446);
        socket.send(packet);
        socket.close();
    }
}