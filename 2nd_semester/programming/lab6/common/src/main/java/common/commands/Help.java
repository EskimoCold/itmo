package common.commands;

import common.handlers.CollectionHandler;
import common.handlers.IOHandler;
import common.handlers.PackageParser;
import common.network.Response;

import java.io.Serial;
import java.util.Arrays;
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
    public Response execute(CollectionHandler collectionHandler) {
        Set<Command> commands = PackageParser.getAllCommands();

        StringBuilder output = new StringBuilder();

        for (Command command : commands) {
            output.append(command.getDescription());
        }

        return new Response(output.toString());
    }
}
