package nodes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Employee;
import protocols.TCPServer;
import protocols.UDP;
import utils.XMLParser;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class DataNode extends Thread {

    private File file;
    private Node node;

    public DataNode(Node node, String file) {
        this.node = node;
        this.file = new File("C:/Users/ArtiomDiana/projects/distributed-systems/labs/lab2/src/main/resources/config/" + file);
    }
    public void run() {
        try {

            ArrayList<Node> nodes = XMLParser.getNodeList(this.file);
            ArrayList<InetSocketAddress> addresses = new ArrayList<>();

            ArrayList<Node> nods = new ArrayList<>();
            for (Node nod : nodes) {
                int a = Integer.parseInt(nod.getLocation().toString().substring(nod.getLocation().toString().length() - 1));
                if (nod.getLocation().equals(this.node.getLocation())) {
                    this.node.setEmployees(this.getNodeEmployees(a));
                } else {
                    addresses.add(nod.getLocation());
                    nod.setEmployees(this.getNodeEmployees(a));
                    nods.add(nod);
                }
            }

            this.node.setLinksAdresses(addresses);
            this.node.setLinksNumber(addresses.size());

            UDP udp = new UDP();
            udp.sendInfo(this.node);

            new Thread(() -> new TCPServer(this.node).start(nods)).start();
            Thread.sleep(200);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Employee> getNodeEmployees(int a) {
        String dataFile = "C:/Users/ArtiomDiana/projects/distributed-systems/labs/lab2/src/main/resources/employees/employees" + a;
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            employees = mapper.readValue(
                    new File(String.valueOf(dataFile)),
                    new TypeReference<List<Employee>>() {
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }
}
