package common.commands.collection;

import common.commands.Command;
import common.handlers.CollectionHandler;
import common.handlers.DBHandler;
import common.handlers.PackageParser;
import common.network.Response;

import java.util.Set;

public class Help extends CollectionCommand {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                            - show info about available commands\n";
    }

    @Override
    public Response execute(String[] args, CollectionHandler collectionHandler, DBHandler dbHandler) {
        Set<Command> commands = PackageParser.getAllCommands();

        StringBuilder output = new StringBuilder();

        for (Command command : commands) {
            output.append(command.getDescription());
        }

        return new Response(null, output.toString());
    }
}
