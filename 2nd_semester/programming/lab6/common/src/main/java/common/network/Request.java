package common.network;

import common.collections.LabWork;
import common.commands.Command;
import lombok.Getter;

import java.io.Serializable;

public class Request implements Serializable {
    @Getter
    private String[] args;
    @Getter
    private Command command;
    @Getter
    private LabWork lab = null;

    public Request(Command command, String[] args) {
        this.command = command;
        this.args = args;
    }

    public Request(Command command, String[] args, LabWork lab) {
        this.command = command;
        this.args = args;
        this.lab = lab;
    }
}
