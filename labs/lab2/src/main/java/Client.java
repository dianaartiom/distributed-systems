import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import common.ECommand;
import common.EFieldName;
import common.EOrder;
import common.Message;
import protocols.TCPConnection;
import utils.JsonValidator;

import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {

        Integer choice;
        while (true) {
            do {
                TCPConnection tcpCommunication = new TCPConnection();
                tcpCommunication.startConnection("127.0.0.1", 5555);

                System.out.println(" 1. GET_ALL");
                System.out.println(" 2. SORT ASC");
                System.out.println(" 3. SORT DESC");;
                System.out.println(" select one choice");

                Scanner scan = new Scanner(System.in);
                choice = Integer.parseInt(scan.nextLine());

                switch (choice) {
                    case 1:
                        Message message1 = new Message(ECommand.GET_ALL);
                        tcpCommunication.sendMessage(message1);
                        String response = tcpCommunication.receiveResponse();

//                        if (validateJsonSchema(response)) {
                            System.out.println(response);
//                        }
                        break;

                    case 2:
                        EFieldName fieldName = getChosenFieldName();
                        EOrder eOrder = EOrder.ASCENDING;

                        Message message2 = new Message(ECommand.SORT, fieldName, eOrder);

                        tcpCommunication.sendMessage(message2);
                        String sortedResponse = tcpCommunication.receiveResponse();

                        if (validateJsonSchema(sortedResponse)) {
                            System.out.println(sortedResponse);
                        }
                        break;

                    case 3:
                        fieldName = getChosenFieldName();
                        eOrder = EOrder.DESCENDING;

                        message2 = new Message(ECommand.SORT, fieldName, eOrder);

                        tcpCommunication.sendMessage(message2);
                        sortedResponse = tcpCommunication.receiveResponse();

                        if (validateJsonSchema(sortedResponse)) {
                            System.out.println(sortedResponse);
                        }
                        break;
                        default:
                }
            } while (!choice.equals(-1));
        }
    }

    private static boolean validateJsonSchema(String response) throws IOException, ProcessingException, ProcessingException {
        String path = "C:/Users/ArtiomDiana/projects/distributed-systems/labs/lab2/src/main/resources/";

        BufferedWriter writer = new BufferedWriter(new FileWriter(path + "data/data.json"));
        writer.write(response);
        writer.close();

        File schemaFile = new File(path + "schema/schema.json");
        File dataFile = new File(path + "data/data.json");

        if (JsonValidator.isJsonValid(schemaFile, dataFile)) {
            System.out.println("Schema is Valid!");
            return true;
        } else {
            System.out.println("NOT valid schema!");
            return false;
        }
    }

    private static EFieldName getChosenFieldName() {
        System.out.println("Type by each field: name/surname/salary.    DEFAULT:  field=salary");
        Scanner scan = new Scanner(System.in);
        String field = scan.next();

        EFieldName fieldName;
        if (field.equals(EFieldName.NAME.toString())) {
            fieldName = EFieldName.NAME;
        } else if (field.equals(EFieldName.SURNAME.toString())) {
            fieldName = EFieldName.SURNAME;
        } else {
            fieldName = EFieldName.SALARY;
        }

        return fieldName;
    }
}
