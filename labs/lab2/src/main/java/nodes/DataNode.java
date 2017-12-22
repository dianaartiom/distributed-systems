package nodes;

import protocols.UDP;

import java.util.ArrayList;
import java.util.List;

public class DataNode extends Thread {

    private Node node;

    public DataNode(Node node) {
        this.node = node;
    }
    public void run() {
        try {
            UDP udp = new UDP();
            List<String> tempList = new ArrayList<>();
            tempList.add("arstarstarst");
            this.node.setMessage("Message from node");
            udp.sendInfo(this.node);
            System.out.println(node.getMessage());
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
