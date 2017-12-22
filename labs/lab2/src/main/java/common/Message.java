package common;

import java.io.Serializable;

public class Message implements Serializable{
    private static final long serialVersionUID = 1L;
    private ECommand command;
    private EFieldName fieldName;
    private EOrder order;

    public Message() {
    }

    public Message(ECommand command) {
        this.command = command;
    }

    public Message(ECommand command, EFieldName fieldName, EOrder order) {
        this.command = command;
        this.fieldName = fieldName;
        this.order = order;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public ECommand getCommand() {
        return command;
    }

    public void setCommand(ECommand command) {
        this.command = command;
    }

    public EFieldName getFieldName() {
        return fieldName;
    }

    public void setFieldName(EFieldName fieldName) {
        this.fieldName = fieldName;
    }

    public EOrder getOrder() {
        return order;
    }

    public void setOrder(EOrder order) {
        this.order = order;
    }


    @Override
    public String toString() {
        return "Message{" +
                "command=" + command +
                ", fieldName=" + fieldName +
                ", order=" + order +
                '}';
    }
}
