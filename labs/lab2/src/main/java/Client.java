import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world");

        Socket socket = new Socket("localhost", 8080);

        PrintWriter out =
                new PrintWriter(socket.getOutputStream(), true);
        out.write("arst");
//        out.close();
//        socket.close();
    }
}
