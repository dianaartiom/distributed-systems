package nodes;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {

        new DataNode(new Node(new InetSocketAddress("localhost", 8080))).run();
        new DataNode(new Node(new InetSocketAddress("localhost", 8081))).run();
        new DataNode(new Node(new InetSocketAddress("localhost", 8082))).run();
    }
}
