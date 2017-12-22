package protocols;

import nodes.Node;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class UDP {
    private int mcPort = 12345;
    private String mcIP = "230.1.1.1";

    public ArrayList<Node> receiveInfoAboutRunningNodes() {
        MulticastSocket mcSocket = null;
        InetAddress mcIPAddress = null;
        ArrayList<Node> nodes = new ArrayList<>();
        try {
            mcSocket = new MulticastSocket(this.mcPort);
            mcIPAddress = InetAddress.getByName(this.mcIP);

            mcSocket.joinGroup(mcIPAddress);
            System.out.println("Nodes please tell me about you");
            mcSocket.setSoTimeout(5000);

            while (true) {
                try {
                    //Create buffer
                    byte[] buffer = new byte[1024];
                    mcSocket.receive(new DatagramPacket(buffer, 1024));

                    //Deserialze object
                    ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                    ObjectInputStream ois = new ObjectInputStream(bais);

                    Object readObject = ois.readObject();
                    if (readObject instanceof Node) {
                        Node node = (Node) readObject;
                        nodes.add(node);
                        System.out.println("Received node info: " + node.toString());
                    } else {
                        System.out.println("The received object is not of type String!");
                    }
                } catch (SocketTimeoutException e) {
                    break;
                } catch (Exception e) {
                    System.out.println("No object could be read from the received UDP datagram.");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                mcSocket.leaveGroup(mcIPAddress);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            mcSocket.close();
        }

        return nodes;
    }

    public void sendInfo(Node node) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(node);
        oos.flush();
        // get the byte array of the object
        byte[] buf = baos.toByteArray();
        DatagramSocket udpSocket = new DatagramSocket();
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        InetAddress mcIPAddress = InetAddress.getByName(this.mcIP);
        packet.setAddress(mcIPAddress);
        packet.setPort(this.mcPort);
        udpSocket.send(packet);

        System.out.println("Sent a  multicast message from node with ip and port:" + node.getLocation());
        udpSocket.close();
    }
}
