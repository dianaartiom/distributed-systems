package common;

import java.io.Serializable;

public enum ECommand implements Serializable {
    GET_ALL("all"), SORT("sort");

    private String command;

    ECommand() {
    }

    ECommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return command;
    }
}
