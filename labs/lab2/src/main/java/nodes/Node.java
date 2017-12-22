package nodes;

import common.Employee;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.ArrayList;

public class Node implements Serializable {

    private InetSocketAddress location;
    private int linksNumber;
    private ArrayList<Employee> employees;
    private ArrayList<InetSocketAddress> linksAdresses;

    public Node(InetSocketAddress inetSocketAddress ) {
        this.location = inetSocketAddress;

    }

    public int getLinksNumber() {
        return linksNumber;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setLinksNumber(int linksNumber) {
        this.linksNumber = linksNumber;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public void setLocation(InetSocketAddress location) {
        this.location = location;
    }

    public void setLinksAdresses(ArrayList<InetSocketAddress> linksAdresses) {
        this.linksAdresses = linksAdresses;
    }

    public InetSocketAddress getLocation() {
        return location;
    }

    public ArrayList<InetSocketAddress> getLinksAdresses() {
        return linksAdresses;
    }

    @Override
    public String toString() {
        return "Node{" +
                "location=" + location +
                ", linksNumber=" + linksNumber +
                ", employees=" + employees +
                ", linksAdresses=" + linksAdresses +
                '}';
    }
}
