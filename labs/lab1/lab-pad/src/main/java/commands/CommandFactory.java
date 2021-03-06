package commands;

import broker.MessageReceivedFromClient;
import data.QueueData;
import data.messages.Message;

public class CommandFactory {

    private QueueData queueData;

    public CommandFactory(QueueData queueData) {
        this.queueData = queueData;
    }

    public Command getCommandForMessage(MessageReceivedFromClient message) {
        Commands commandByIndex = Commands.getCommandByIndex(message.getMessage().getCommand());
        switch (commandByIndex) {
            case CREATE_QUEUE:
                return new CreateQueueCommand(this.queueData, message);
            case DELETE_QUEUE:
                return new RemoveQueueCommand(this.queueData, message);
            case ROUTE_DIRECT_MESSAGE:
                return new RouteDirectMessageCommand(this.queueData, message);
            case ROUTE_TOPIC_MESSAGE:
                return new RouteTopicMessageCommand(this.queueData, message);
            case GET_MESSAGE_FROM_QUEUE:
                return new GetMessageCommand(this.queueData, message);
            case SUBSCRIBE:
                return new SubscribeCommand(this.queueData, message);
            case GET_TOPIC_MESSAGE:
                return new GetTopicMessageCommand(this.queueData, message);
            case BROADCAST:
                return new BroadcastCommand(this.queueData, message);
            default:
                return new NoSuchCommand(this.queueData, message);
        }
    }

    public enum Commands {
        CREATE_QUEUE(0),
        DELETE_QUEUE(1),
        ROUTE_DIRECT_MESSAGE(2),
        ROUTE_TOPIC_MESSAGE(3),
        GET_MESSAGE_FROM_QUEUE(4),
        NO_SUCH_COMMAND(5),
        SUBSCRIBE(6),
        GET_TOPIC_MESSAGE(7),
        BROADCAST(8);

        private int commandIndex;

        Commands(int commandIndex) {
            this.commandIndex = commandIndex;
        }

        public int getCommandIndex(){
            return this.commandIndex;
        }

        public static Commands getCommandByIndex(int commandIndex){
            for (Commands command : values()) {
                if (command.getCommandIndex() == commandIndex){
                    return command;
                }
            }
            return NO_SUCH_COMMAND;
        }
    }
}
