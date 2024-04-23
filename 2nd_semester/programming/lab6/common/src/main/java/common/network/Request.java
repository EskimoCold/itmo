package common.network;

import common.collections.LabWork;
import common.commands.Command;
import lombok.Getter;

import java.io.Serializable;

public class Request implements Serializable {
    @Getter
    private String[] args = null;
    @Getter
    private Command command;
    @Getter
    private LabWork lab = null;

    public Request(Command command) {
        this.command = command;
    }

    public Request(Command command, LabWork lab) {
        this.command = command;
        this.lab = lab;
    }
}
