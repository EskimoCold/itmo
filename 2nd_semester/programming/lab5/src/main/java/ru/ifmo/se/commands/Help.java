package ru.ifmo.se.commands;

import ru.ifmo.se.handlers.CollectionHandler;
import ru.ifmo.se.handlers.IOHandler;
import ru.ifmo.se.handlers.PackageParser;

import java.util.Arrays;
import java.util.Set;

public class Help implements Command {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return this.getName() + "                            - show info about available commands\n";
    }

    @Override
    public void execute(CollectionHandler collectionHandler, String[] args) {
        Set<Command> commands = PackageParser.getAllCommands();

        StringBuilder output = new StringBuilder();

        for (Command command : commands) {
            output.append(command.getDescription());
        }

        IOHandler.println(output);
    }
}
