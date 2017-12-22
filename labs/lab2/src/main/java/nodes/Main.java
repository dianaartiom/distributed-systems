package nodes;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws IOException {

        new DataNode( new Node(new InetSocketAddress("127.0.0.1", 7000)),"conf0.xml").run();
        new DataNode( new Node(new InetSocketAddress("127.0.0.1", 7001)),"conf1.xml").run();
        new DataNode( new Node(new InetSocketAddress("127.0.0.1", 7002)),"conf2.xml").run();
        new DataNode( new Node(new InetSocketAddress("127.0.0.1", 7003)),"conf3.xml").run();
        new DataNode( new Node(new InetSocketAddress("127.0.0.1", 7004)),"conf4.xml").run();
        new DataNode( new Node(new InetSocketAddress("127.0.0.1", 7005)),"conf5.xml").run();

    }
}
