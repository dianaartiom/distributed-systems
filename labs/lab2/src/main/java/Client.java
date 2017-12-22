import protocols.TCPConnection;

import java.io.*;

public class Client {
    public static void main(String[] args) throws Exception {
        TCPConnection tcpCommunication = new TCPConnection();
        tcpCommunication.startConnection("127.0.0.1", 5555);
        tcpCommunication.sendMessage("Client says hello.");
        String response = tcpCommunication.receiveResponse();
        System.out.println(response);
    }
}
