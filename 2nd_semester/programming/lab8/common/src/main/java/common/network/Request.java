package common.network;

import common.collections.LabWork;
import common.commands.Command;
import lombok.Getter;

import java.io.Serializable;

public class Request implements Serializable {
    @Getter
    private User user;
    @Getter
    private String[] args;
    @Getter
    private Command command;
    @Getter
    private LabWork lab = null;

    public Request(User user, Command command, String[] args) {
        this.user = user;
        this.command = command;
        this.args = args;
    }

    public Request(User user, Command command, String[] args, LabWork lab) {
        this.user = user;
        this.command = command;
        this.args = args;
        this.lab = lab;
    }
}
