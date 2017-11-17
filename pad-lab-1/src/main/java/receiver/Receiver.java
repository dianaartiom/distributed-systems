package receiver;

import org.xml.sax.SAXException;
import sender.Sender;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutionException;

public class Receiver extends Sender{

    public Receiver() throws IOException, InterruptedException, ExecutionException, TransformerException, SAXException, JAXBException, ParserConfigurationException {
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(null);
        server.bind(new InetSocketAddress("localhost",6788));
        Attachment attachment = new Attachment();
        attachment.setServer(server);
        this.connectAndSendMsg(true,"Vasile",null,"localhost",6788L);
        server.accept(attachment, new ConnectionHandler());
        Thread.currentThread().join();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, TransformerException, IOException, JAXBException, ParserConfigurationException, SAXException {
        Receiver reciver = new Receiver();
    }


}