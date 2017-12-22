package protocols;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPConnection {

    private Socket clientSocket;
    private PrintWriter out;

    public void startConnection(String ip, int port) throws Exception {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public String receiveResponse() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        return in.readLine();
    }

    public static String receiveMessage(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        return in.readLine();
    }
//
//    public String receiveMessageInJson(Socket clientSocket) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//        return in.readLine();
//    }

    public void sendMessage(String message) throws IOException {
        out.println(message);
    }
}
