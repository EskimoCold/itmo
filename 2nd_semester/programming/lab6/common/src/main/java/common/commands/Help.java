package common.commands;

import common.collections.LabWork;
import common.handlers.PackageParser;
import common.network.Response;

import java.util.ArrayDeque;
import java.util.Set;

public class Help extends Command {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                            - show info about available commands\n";
    }

    @Override
    public Response execute(ArrayDeque<LabWork> collection) {
        Set<Command> commands = PackageParser.getAllCommands();

        StringBuilder output = new StringBuilder();

        for (Command command : commands) {
            output.append(command.getDescription());
        }

        return new Response(output.toString());
    }
}
